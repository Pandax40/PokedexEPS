package com.pandas.pokedexeps.services

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

import com.pandas.pokedexeps.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EventServiceAdapter : IEventService {

    private val api: EventApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceLocator.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(EventApi::class.java)
    }

    override suspend fun generateEvent(zoneId: String): Event = withContext(Dispatchers.IO) {
        val hardcodedTeamId = "c588d253-e2b5-4e25-9b0c-1252f747e451"
        val request = GenerateEventRequest(team_id = hardcodedTeamId)
        val response = api.generateEvent(zoneId, request)
        mapEventResponseToModel(response)
    }

    private fun mapEventResponseToModel(response: GenerateEventResponse): Event {
        return Event(
            teamId = response.team_id,
            capturedPokemonUuid = response.captured_pokemon_uuid,
            capturedPokemons = response.pokemon_uuid_list.map {
                CapturedPokemon(
                    id = it.id,
                    pokemonId = it.pokemon_id
                )
            }
        )
    }

    interface EventApi {
        @POST("events/{zone_id}")
        suspend fun generateEvent(
            @Path("zone_id") zoneId: String,
            @Body request: GenerateEventRequest
        ): GenerateEventResponse
    }
}
