package com.pandas.pokedexeps.models


data class Event(
    val teamId: String,
    val capturedPokemonUuid: String,
    val capturedPokemons: List<CapturedPokemon>
)

