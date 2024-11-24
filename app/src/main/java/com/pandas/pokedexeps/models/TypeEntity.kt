package com.pandas.pokedexeps.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type_table")
data class TypeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pokemonId: Int, // Foreign key
    val name: String,
    val url: String,
    val slot: Int
)
