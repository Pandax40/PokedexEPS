package com.pandas.pokedexeps.algorithm

import com.pandas.pokedexeps.models.Pokemon

interface AlgorithmTeamGeneration {
    fun findBestTeam(): List<Pokemon>
}