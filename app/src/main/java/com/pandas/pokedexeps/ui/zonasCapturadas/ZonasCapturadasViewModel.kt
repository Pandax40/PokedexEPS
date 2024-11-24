package com.pandas.pokedexeps.ui.zonasCapturadas

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandas.pokedexeps.adapter.AdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ZonasCapturadasViewModel : ViewModel() {
    // Estado del Switch de automatización
    private val _isAutomatizarOn = mutableStateOf(false)
    val isAutomatizarOn: State<Boolean> = _isAutomatizarOn

    // Mapa de zonas capturadas
    private val _zonasCapturadasMap = mutableStateOf<Map<String, Int>>(emptyMap())
    val zonasCapturadasMap: State<Map<String, Int>> = _zonasCapturadasMap

    // Estado del pop-up
    var showCooldownPopup: MutableState<Boolean> = mutableStateOf(false)
        private set

    // Job secundario para la automatización
    private var automationJob: Job? = null

    init {
        fetchCapturedZones()
    }

    // Método para alternar el estado del switch
    fun toggleAutomatizarCapturas(isOn: Boolean) {
        _isAutomatizarOn.value = isOn
        if (isOn) {
            startAutomation()
        } else {
            stopAutomation()
        }
    }

    // Inicia la automatización si está activada
    private fun startAutomation() {
        automationJob = viewModelScope.launch {
            while (true) {
                val zonas = _zonasCapturadasMap.value.keys
                zonas.forEach { zoneId ->
                    checkAndCapture(zoneId)
                }
                delay(60000) // Esperar 1 minuto
            }
        }
    }

    // Detiene la automatización si está activa
    private fun stopAutomation() {
        automationJob?.cancel()
        automationJob = null
    }

    // Método para comprobar y capturar Pokémon automáticamente
    private suspend fun checkAndCapture(zone: String) {
        withContext(Dispatchers.IO) {
            try {

                val zoneService = AdapterFactory.createZoneServiceAdapter()

                val zoneO = zoneService.getZoneById(zone)


                // Comprueba si el equipo está en la lista de espera
                val isOnWaitingList = zoneO.lastRequestsByTeam.any { it.name == "Panda Enjoyers" }

                if (!isOnWaitingList) {
                    val eventService = AdapterFactory.createEventServiceAdapter()
                    eventService.generateEvent(zone)
                    println("Pokémon capturado automáticamente en la zona: $zone")
                } else {
                    println("Equipo en lista de espera para la zona: $zone")
                }
            } catch (e: Exception) {
                println("Error en checkAndCapture para la zona $zone: ${e.message}")
            }
        }
    }

    // Intenta capturar un Pokémon manualmente
    fun attemptCapture(zoneId: String) {
        viewModelScope.launch {
            try {

                val zoneService = AdapterFactory.createZoneServiceAdapter()

                val zone = zoneService.getZoneById(zoneId)


                // Comprueba si el equipo está en la lista de espera
                val isOnWaitingList = zone.lastRequestsByTeam.any { it.name == "Panda Enjoyers" }

                if (isOnWaitingList) {
                    showCooldownPopup.value = true // Activa el pop-up
                } else {
                    val eventService = AdapterFactory.createEventServiceAdapter()
                    eventService.generateEvent(zoneId)
                    println("Pokémon capturado manualmente en la zona: $zoneId")
                }
            } catch (e: Exception) {
                println("Error intentando capturar en la zona $zoneId: ${e.message}")
            }
        }
    }

    // Oculta el pop-up
    fun dismissCooldownPopup() {
        showCooldownPopup.value = false
    }

    // Carga las zonas capturadas al iniciar
    private fun fetchCapturedZones() {
        viewModelScope.launch {
            try {
                val teamService = AdapterFactory.createTeamServiceAdapter()
                val pokemonService = AdapterFactory.createPokemonServiceAdapter()

                // Paso 1: Obtener el equipo capturado
                val team = teamService.getTeam()
                val capturedPokemonIds = team.capturedPokemons.map { it.pokemonId }

                // Paso 2: Obtener todos los Pokémon (solo una llamada a la API)
                val allPokemons = pokemonService.getAllPokemons()

                // Paso 3: Filtrar solo los Pokémon capturados
                val capturedPokemons = allPokemons.filter { it.id in capturedPokemonIds }

                // Paso 4: Crear un mapa único de zonas
                val locationMap = mutableMapOf<String, Int>()
                var currentIndex = 1

                capturedPokemons.forEach { pokemon ->
                    pokemon.locations.forEach { location ->
                        if (!locationMap.containsKey(location)) {
                            locationMap[location] = currentIndex++
                        }
                    }
                }

                // Paso 5: Actualizar el estado con las zonas capturadas
                _zonasCapturadasMap.value = locationMap

                // Logging para depuración
                println("Captured Pokémon IDs: $capturedPokemonIds")
                println("Captured Pokémons: $capturedPokemons")
                println("Location Map: $locationMap")

            } catch (e: Exception) {
                // Manejar errores
                println("Error fetching captured zones: ${e.message}")
            }
        }
    }
}


