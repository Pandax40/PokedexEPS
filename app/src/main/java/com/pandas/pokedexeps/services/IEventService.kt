package com.pandas.pokedexeps.services


import com.pandas.pokedexeps.models.Event

interface IEventService {
    suspend fun generateEvent(zoneId: String, teamId: String): Event
}