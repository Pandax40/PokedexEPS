package com.pandas.pokedexeps.ui.team

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandas.pokedexeps.adapter.AdapterFactory
import com.pandas.pokedexeps.models.Team
import kotlinx.coroutines.launch

class TeamViewModel : ViewModel() {
    private val _team = mutableStateOf<Team?>(null)
    val team: State<Team?> = _team

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        fetchPokemonData()
    }

    private fun fetchPokemonData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val teamService = AdapterFactory.createTeamServiceAdapter()
                _team.value = teamService.getTeam()
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}