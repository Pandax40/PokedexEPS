package com.pandas.pokedexeps.models

data class PokemonDTO(
    val id: Int,
    val name: String,
    val abilities: List<AbilityDTO>,
    val image: String,
    val types: List<TypeDTO>
)

