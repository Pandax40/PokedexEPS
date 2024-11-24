package com.pandas.pokedexeps.ui.pokedex

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

import com.pandas.pokedexeps.models.Pokemon
import com.pandas.pokedexeps.adapter.AdapterFactory

class PokedexViewModel : ViewModel() {
    private val _pokemonList = mutableStateOf<List<Pokemon>>(emptyList())
    private val _pokemonOwnList = mutableStateOf<List<String>>(emptyList())
    val pokemonList: State<List<Pokemon>> = _pokemonList
    val pokemonOwnList: State<List<String>> = _pokemonOwnList

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        fetchPokemonData()
    }

    private val _sortingOption = mutableStateOf(SortingOption.BY_ID)
    val sortingOption: State<SortingOption> = _sortingOption

    // Function to set the sorting option and sort the list
    fun setSortingOption(option: SortingOption) {
        _sortingOption.value = option
        sortPokemonList()
    }

    private fun sortPokemonList() {
        val sortedList = when (_sortingOption.value) {
            SortingOption.BY_NAME -> _pokemonList.value.sortedBy { it.name.lowercase() }
            SortingOption.BY_ID -> _pokemonList.value.sortedBy { it.id }
        }
        _pokemonList.value = sortedList
    }

    private fun fetchPokemonData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val pokemonService = AdapterFactory.createPokemonServiceAdapter()
                val pokemons = pokemonService.getAllPokemons()
                val teamService = AdapterFactory.createTeamServiceAdapter()
                val ownIdPokemons = teamService.getTeam().capturedPokemons
                _pokemonList.value = pokemons
                _pokemonOwnList.value = ownIdPokemons.map { it.pokemonId.toString() }
                sortPokemonList() // Sort after fetching data
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}
// PokedexViewModel.kt
enum class SortingOption {
    BY_NAME,
    BY_ID
}