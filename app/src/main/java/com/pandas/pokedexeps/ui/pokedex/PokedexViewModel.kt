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
    val pokemonList: State<List<Pokemon>> = _pokemonList

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        fetchPokemonData()
    }

    private fun fetchPokemonData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val pokemonService = AdapterFactory.createPokemonServiceAdapter()
                val pokemons = pokemonService.getAllPokemons()
                _pokemonList.value = pokemons
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}