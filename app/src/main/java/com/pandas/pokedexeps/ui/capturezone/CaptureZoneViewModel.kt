package com.pandas.pokedexeps.ui.capturezone

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

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
    }

    fun onBackButtonClicked() {
        navigateBackToHomeScreen.value = true
    }

    fun onNavigatedBackToHomeScreen() {
        navigateBackToHomeScreen.value = false
    }
}