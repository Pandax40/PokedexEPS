package com.pandas.pokedexeps.database

import androidx.room.*
import com.pandas.pokedexeps.models.*

@Dao
interface MoveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoves(moves: List<MoveEntity>)

    @Query("SELECT * FROM move_table WHERE pokemonId = :pokemonId")
    suspend fun getMovesForPokemon(pokemonId: Int): List<MoveEntity>
}
