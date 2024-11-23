package com.pandas.pokedexeps.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var navigateToCaptureZoneScreen: MutableState<Boolean> = mutableStateOf(false)
        private set

    fun onCaptureZoneButtonClicked() {
        navigateToCaptureZoneScreen.value = true
    }

    fun onNavigatedToCaptureZoneScreen() {
        navigateToCaptureZoneScreen.value = false
    }
}