package com.pandas.pokedexeps.database

import androidx.room.*
import com.pandas.pokedexeps.models.*

@Dao
interface TypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypes(types: List<TypeEntity>)

    @Query("SELECT * FROM type_table WHERE pokemonId = :pokemonId")
    suspend fun getTypesForPokemon(pokemonId: Int): List<TypeEntity>
}
