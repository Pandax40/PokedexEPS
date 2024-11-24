package com.pandas.pokedexeps.models


import androidx.room.Embedded
import androidx.room.Relation

data class PokemonWithDetails(
    @Embedded val pokemon: PokemonEntity,
    @Relation(parentColumn = "id", entityColumn = "pokemonId")
    val abilities: List<AbilityEntity>,
    @Relation(parentColumn = "id", entityColumn = "pokemonId")
    val types: List<TypeEntity>,
    @Relation(parentColumn = "id", entityColumn = "pokemonId")
    val moves: List<MoveEntity>,
    @Relation(parentColumn = "id", entityColumn = "pokemonId")
    val stats: List<StatEntity>,
    @Relation(parentColumn = "id", entityColumn = "pokemonId")
    val evolutions: List<EvolutionEntity>,
    @Relation(parentColumn = "id", entityColumn = "pokemonId")
    val locations: List<LocationEntity>
)
