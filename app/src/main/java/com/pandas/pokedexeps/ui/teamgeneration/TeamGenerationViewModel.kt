package com.pandas.pokedexeps.ui.teamgeneration

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.pandas.pokedexeps.models.Pokemon
import com.pandas.pokedexeps.adapter.AdapterFactory
import com.pandas.pokedexeps.algorithm.GeneticAlgorithm

class TeamGenerationViewModel : ViewModel() {
    private val _teamList = mutableStateOf<List<Pokemon>>(emptyList())
    val teamList: State<List<Pokemon>> = _teamList

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _allPokemons = mutableStateOf<List<Pokemon>>(emptyList())
    val allPokemons: State<List<Pokemon>> = _allPokemons

    init {
        fetchPokemonData()
    }

    private fun fetchPokemonData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val teamService = AdapterFactory.createTeamServiceAdapter()
                val ownIdPokemons = teamService.getTeam().capturedPokemons.map { it.pokemonId.toString() }

                val pokemonService = AdapterFactory.createPokemonServiceAdapter()
                val pokemons = pokemonService.getAllPokemons()

                // Filtrar los Pokémon que el usuario posee
                _allPokemons.value = pokemons.filter { it.id.toString() in ownIdPokemons }
                _isLoading.value = false
            } catch (e: Exception) {
                // Manejar error
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun generateBestTeam() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val geneticAlgorithm = GeneticAlgorithm(allPokemons.value)
                val bestTeam = geneticAlgorithm.findBestTeam()
                _teamList.value = bestTeam
            } catch (e: Exception) {
                // Manejar error
            } finally {
                _isLoading.value = false
            }
        }
    }


}
