package com.pandas.pokedexeps.services


import com.pandas.pokedexeps.models.Team

interface ITeamService {
    suspend fun getTeam(): Team
}