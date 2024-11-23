package com.pandas.pokedexeps.adapter

import com.pandas.pokedexeps.services.IPokemonService
import com.pandas.pokedexeps.services.PokemonServiceAdapter


object AdapterFactory {

    fun createPokemonServiceAdapter(): IPokemonService {
        return PokemonServiceAdapter()
    }
}
