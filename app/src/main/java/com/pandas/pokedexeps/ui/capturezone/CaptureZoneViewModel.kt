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

    fun onQrCodeScanned(scannedQrCode: String) {
        qrCode.value = scannedQrCode
        areButtonsVisible.value = true
    }

    fun registerZone() {
        // Logic to register the zone using the scanned QR code
    }

    fun capturePokemon() {
        // Logic to capture Pokémon using the scanned QR code
        val zone = qrCode.value.takeLast(24)

        capturePokemon(zone)
    }

    private fun capturePokemon(zone: String) {
        viewModelScope.launch {
            // Logic to capture Pokémon using the scanned QR code

            val teamService = AdapterFactory.createTeamServiceAdapter()

            val zoneService = AdapterFactory.createZoneServiceAdapter();
            val zoneO = zoneService.getZoneById(zone)
            var encontrado = false;
            zoneO.lastRequestsByTeam.forEach { teamId ->
                val team = teamService.getTeamById(teamId)
                if (team.name == "PandaEnjoyer") {
                    encontrado = true
                }
            }

            if(!encontrado) {
                // Realiza las acciones necesarias si ningun equipo es "PandaEnjoyer"
                val eventServie = AdapterFactory.createEventServiceAdapter();
                eventServie.generateEvent(zone)
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