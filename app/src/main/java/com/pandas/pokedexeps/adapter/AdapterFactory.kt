package com.pandas.pokedexeps.adapter

import com.pandas.pokedexeps.services.IPokemonService
import com.pandas.pokedexeps.services.ITeamService
import com.pandas.pokedexeps.services.PokemonServiceAdapter
import com.pandas.pokedexeps.services.TeamServiceAdapter


object AdapterFactory {

    fun createPokemonServiceAdapter(): IPokemonService {
        return PokemonServiceAdapter()
    }

    fun createTeamServiceAdapter(): ITeamService {
        return TeamServiceAdapter()
    }
}
