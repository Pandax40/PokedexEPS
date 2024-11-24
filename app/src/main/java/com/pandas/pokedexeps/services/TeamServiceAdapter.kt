package com.pandas.pokedexeps.services



import com.pandas.pokedexeps.models.Ability
import com.pandas.pokedexeps.models.CapturedPokemon
import com.pandas.pokedexeps.models.Team
import com.pandas.pokedexeps.models.TeamDTO

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class TeamServiceAdapter : ITeamService {

    private val api: TeamApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceLocator.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(TeamApi::class.java)
    }

    override suspend fun getTeam(): Team = withContext(Dispatchers.IO) {
        val hardcodedTeamId = "c588d253-e2b5-4e25-9b0c-1252f747e451"
        val teamDTO = api.getTeamById(hardcodedTeamId)
        mapTeamDTOToModel(teamDTO)
    }

    private fun mapTeamDTOToModel(dto: TeamDTO): Team {
        return Team(
            id = dto.id,
            name = dto.name,
            pveScore = dto.pve_score,
            pvpScore = dto.pvp_score,
            pokedexScore = dto.pokedex_score,
            capturedPokemons = dto.captured_pokemons.map {
                CapturedPokemon(
                    id = it.id,
                    pokemonId = it.pokemon_id
                )
            },
            isActive = dto.is_active
        )
    }

    interface TeamApi {
        @GET("teams/{team_id}")
        suspend fun getTeamById(@Path("team_id") teamId: String): TeamDTO
    }
}