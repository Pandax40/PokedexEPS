package com.pandas.pokedexeps.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "move_table")
data class MoveEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pokemonId: Int, // Foreign key
    val name: String,
    val url: String
)

