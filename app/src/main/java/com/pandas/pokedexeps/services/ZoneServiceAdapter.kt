package com.pandas.pokedexeps.services

import com.pandas.pokedexeps.models.PokemonProbability
import com.pandas.pokedexeps.models.Zone
import com.pandas.pokedexeps.models.ZoneDTO

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class ZoneServiceAdapter : IZoneService {

    private val api: ZoneApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceLocator.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(ZoneApi::class.java)
    }

    override suspend fun getZoneById(zoneId: String): Zone = withContext(Dispatchers.IO) {
        val zoneDTO = api.getZoneById(zoneId)
        mapZoneDTOToModel(zoneDTO)
    }

    private fun mapZoneDTOToModel(dto: ZoneDTO): Zone {
        return Zone(
            id = dto._id,
            name = dto.name,
            cooldownPeriod = dto.cooldown_period,
            lastRequestsByTeam = dto.last_requests_by_team,
            pokemonProbabilities = dto.pokemon_prob_list.map {
                PokemonProbability(
                    pokemonId = it.pokemon_id,
                    probability = it.probability
                )
            }
        )
    }

    interface ZoneApi {
        @GET("zones/{zone_id}")
        suspend fun getZoneById(@Path("zone_id") zoneId: String): ZoneDTO
    }
}