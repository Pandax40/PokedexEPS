package com.pandas.pokedexeps.database

import androidx.room.*
import com.pandas.pokedexeps.models.*

@Dao
interface EvolutionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvolutions(evolutions: List<EvolutionEntity>)

    @Query("SELECT * FROM evolution_table WHERE pokemonId = :pokemonId")
    suspend fun getEvolutionsForPokemon(pokemonId: Int): List<EvolutionEntity>
}
