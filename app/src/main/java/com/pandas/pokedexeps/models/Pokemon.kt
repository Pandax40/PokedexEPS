package com.pandas.pokedexeps.models

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>
)
