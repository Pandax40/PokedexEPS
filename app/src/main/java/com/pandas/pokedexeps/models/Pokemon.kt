package com.pandas.pokedexeps.models


data class Pokemon(
    val id: Int,
    val name: String,
    val abilities: List<Ability>,
    val cries: String,
    val height: Int,
    val locations: List<String>,
    val evolvesTo: Evolution?,
    val moves: List<Move>,
    val species: Species,
    val imageUrl: String,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)


