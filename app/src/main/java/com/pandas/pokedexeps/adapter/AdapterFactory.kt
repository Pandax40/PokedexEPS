package com.pandas.pokedexeps.adapter

import com.pandas.pokedexeps.services.EventServiceAdapter
import com.pandas.pokedexeps.services.IEventService
import com.pandas.pokedexeps.services.IPokemonService
import com.pandas.pokedexeps.services.ITeamService

import com.pandas.pokedexeps.services.IZoneService
import com.pandas.pokedexeps.services.PokemonServiceAdapter
import com.pandas.pokedexeps.services.TeamServiceAdapter
import com.pandas.pokedexeps.services.ZoneServiceAdapter

import com.pandas.pokedexeps.services.IIAService

import com.pandas.pokedexeps.services.IAServiceAdapter



object AdapterFactory {

    fun createPokemonServiceAdapter(): IPokemonService {
        return PokemonServiceAdapter()
    }

    fun createTeamServiceAdapter(): ITeamService {
        return TeamServiceAdapter()
    }

    fun createZoneServiceAdapter(): IZoneService {
        return ZoneServiceAdapter()
    }


    fun createEventServiceAdapter(): IEventService {
        return EventServiceAdapter()
    }


    fun createIAServiceAdapter(): IIAService {
        return IAServiceAdapter()

    }
}
