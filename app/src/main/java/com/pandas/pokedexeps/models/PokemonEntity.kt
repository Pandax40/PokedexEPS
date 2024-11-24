package com.pandas.pokedexeps.models


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_table")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val cries: String,
    val height: Int,
    val imageUrl: String,
    val weight: Int,
    val speciesName: String,
    val speciesUrl: String
)
