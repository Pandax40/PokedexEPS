package com.pandas.pokedexeps.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
    var navigateToCaptureZoneScreen: MutableState<Boolean> = mutableStateOf(false)
        private set

    var navigateToPokedexScreen: MutableState<Boolean> = mutableStateOf(false)
        private set

    var navigateToTeamScreen: MutableState<Boolean> = mutableStateOf(false)
        private set

    var navitateToPokemonDetail: MutableState<Boolean> = mutableStateOf(false)
        private set

    fun onCaptureZoneButtonClicked() {
        navigateToCaptureZoneScreen.value = true
    }

    fun onPokedexButtonClicked() {
        navigateToPokedexScreen.value = true
    }

    fun onTeamButtonClicked() {
        navigateToTeamScreen.value = true
    }

    fun onNavigatedToCaptureZoneScreen() {
        navigateToCaptureZoneScreen.value = false
    }

    fun onNavigatedToPokedexScreen() {
        navigateToPokedexScreen.value = false
    }

    fun onNavigatedToTeamScreen() {
        navigateToTeamScreen.value = false
    }

    fun onNavigatedToPokemonDetail() {
        navitateToPokemonDetail.value = false
    }

    fun onPokemonDetailButtonClicked() {
        navitateToPokemonDetail.value = true
    }
}