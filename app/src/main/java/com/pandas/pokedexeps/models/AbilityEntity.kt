package com.pandas.pokedexeps.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ability_table")
data class AbilityEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pokemonId: Int, // Foreign key
    val name: String,
    val url: String,
    val isHidden: Boolean,
    val slot: Int
)
