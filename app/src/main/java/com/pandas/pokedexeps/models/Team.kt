package com.pandas.pokedexeps.models

data class Team(
    val id: String,
    val name: String,
    val pveScore: Int,
    val pvpScore: Int,
    val pokedexScore: Int,
    val capturedPokemons: List<CapturedPokemon>,
    val isActive: Boolean
)