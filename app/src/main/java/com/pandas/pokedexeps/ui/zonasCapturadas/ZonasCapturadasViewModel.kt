package com.pandas.pokedexeps.ui.zonasCapturadas

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandas.pokedexeps.adapter.AdapterFactory
import kotlinx.coroutines.launch

class ZonasCapturadasViewModel : ViewModel() {

    // Estado del Switch de automatización
    private val _isAutomatizarOn = mutableStateOf(false) // Estado inicial del switch
    val isAutomatizarOn: State<Boolean> = _isAutomatizarOn

    // Mapa de zonas capturadas
    private val _zonasCapturadasMap = mutableStateOf<Map<String, Int>>(emptyMap())
    val zonasCapturadasMap: State<Map<String, Int>> = _zonasCapturadasMap

    init {
        fetchCapturedZones()
    }

    // Método para alternar el estado del switch
    fun toggleAutomatizarCapturas(isOn: Boolean) {
        _isAutomatizarOn.value = isOn
    }

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

