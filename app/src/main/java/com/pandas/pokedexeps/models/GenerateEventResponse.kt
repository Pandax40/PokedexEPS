package com.pandas.pokedexeps.models

data class GenerateEventResponse(
    val team_id: String,
    val captured_pokemon_uuid: String,
    val pokemon_uuid_list: List<CapturedPokemonDTO>
)