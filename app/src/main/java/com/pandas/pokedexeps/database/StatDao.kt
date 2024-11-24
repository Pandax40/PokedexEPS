package com.pandas.pokedexeps.database

import androidx.room.*
import com.pandas.pokedexeps.models.*

@Dao
interface StatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(stats: List<StatEntity>)

    @Query("SELECT * FROM stat_table WHERE pokemonId = :pokemonId")
    suspend fun getStatsForPokemon(pokemonId: Int): List<StatEntity>
}
