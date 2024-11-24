package com.pandas.pokedexeps.ui.pokedex.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandas.pokedexeps.adapter.AdapterFactory
import com.pandas.pokedexeps.models.Pokemon
import kotlinx.coroutines.launch

class PokemonDetailModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _pokemonDetail = mutableStateOf<Pokemon?>(null)
    val pokemonDetail: State<Pokemon?> = _pokemonDetail

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        val pokemonId: Int = savedStateHandle["pokemonId"] ?: 0
        fetchPokemonData(pokemonId)
    }

    private fun fetchPokemonData(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val pokemonService = AdapterFactory.createPokemonServiceAdapter()
                val pokemon = pokemonService.getPokemonById(id)
                _pokemonDetail.value = pokemon
            } catch (e: Exception) {
                // Handle error
            } finally {
                _isLoading.value = false
            }
        }
    }
}