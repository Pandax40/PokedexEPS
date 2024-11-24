package com.pandas.pokedexeps.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "evolution_table")
data class EvolutionEntity(
    @PrimaryKey val id: String, // Evolution ID from API
    val pokemonId: Int, // Foreign key
    val name: String
)

