package com.pandas.pokedexeps.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stat_table")
data class StatEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pokemonId: Int, // Foreign key
    val name: String,
    val url: String,
    val baseStat: Int,
    val effort: Int
)
