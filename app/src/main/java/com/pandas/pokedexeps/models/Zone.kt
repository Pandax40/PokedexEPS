package com.pandas.pokedexeps.models


data class Zone(
    val id: String,
    val name: String,
    val cooldownPeriod: Int,
    val lastRequestsByTeam: List<String>,
    val pokemonProbabilities: List<PokemonProbability>
)

