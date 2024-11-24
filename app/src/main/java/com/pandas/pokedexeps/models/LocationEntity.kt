package com.pandas.pokedexeps.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_table")
data class LocationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pokemonId: Int, // Foreign key
    val location: String
)
