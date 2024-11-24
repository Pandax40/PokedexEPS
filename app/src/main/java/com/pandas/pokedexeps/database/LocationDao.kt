package com.pandas.pokedexeps.database

import androidx.room.*
import com.pandas.pokedexeps.models.*

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<LocationEntity>)

    @Query("SELECT * FROM location_table WHERE pokemonId = :pokemonId")
    suspend fun getLocationsForPokemon(pokemonId: Int): List<LocationEntity>
}
