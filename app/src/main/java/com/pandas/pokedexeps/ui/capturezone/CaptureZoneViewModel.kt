package com.pandas.pokedexeps.ui.capturezone

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandas.pokedexeps.adapter.AdapterFactory
import kotlinx.coroutines.launch

class CaptureZoneViewModel : ViewModel() {
    var qrCode: MutableState<String> = mutableStateOf("")
        private set

    var areButtonsVisible: MutableState<Boolean> = mutableStateOf(false)
        private set

    var navigateBackToHomeScreen: MutableState<Boolean> = mutableStateOf(false)
        private set

    // Nueva propiedad para controlar la visualización del pop-up
    var showCooldownPopup: MutableState<Boolean> = mutableStateOf(false)
        private set

    fun onQrCodeScanned(scannedQrCode: String) {
        qrCode.value = scannedQrCode
        areButtonsVisible.value = true
    }

    fun registerZone() {
        // Logic to register the zone using the scanned QR code
    }

    fun capturePokemon() {
        // Logic to capture Pokémon using the scanned QR code
        val zone: String = qrCode.value.toString().takeLast(24)
        capturePokemon(zone)
    }

    private fun capturePokemon(zone: String) {
        viewModelScope.launch {
            val teamService = AdapterFactory.createTeamServiceAdapter()
            val zoneService = AdapterFactory.createZoneServiceAdapter()
            val zoneO = zoneService.getZoneById(zone)
            var encontrado = false

            // Obtenemos el equipo actual una vez
            val team = teamService.getTeam()
            zoneO.lastRequestsByTeam.forEach { teamName ->
                if ( teamName.name == "PandaEnjoyers") {
                    encontrado = true
                }
            }

            if (!encontrado) {
                // Acciones si no se ha encontrado el equipo en la lista (no hay cooldown)
                val eventService = AdapterFactory.createEventServiceAdapter()
                eventService.generateEvent(zone)
            } else {
                // Acciones si el equipo está en la lista (tiene cooldown)
                showCooldownPopup.value = true // Activamos el pop-up
            }
        }
    }

    fun onBackButtonClicked() {
        navigateBackToHomeScreen.value = true
    }

    fun onNavigatedBackToHomeScreen() {
        navigateBackToHomeScreen.value = false
    }
}