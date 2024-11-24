package com.pandas.pokedexeps.models

data class TeamDTO(
    val id: String,
    val name: String,
    val pve_score: Int,
    val pvp_score: Int,
    val pokedex_score: Int,
    val captured_pokemons: List<CapturedPokemonDTO>, // List of captured Pokémon UUIDs
    val is_active: Boolean
)
