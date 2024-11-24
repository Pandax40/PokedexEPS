package com.pandas.pokedexeps.database

import androidx.room.*
import com.pandas.pokedexeps.models.*

@Dao
interface AbilityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilities(abilities: List<AbilityEntity>)

    @Query("SELECT * FROM ability_table WHERE pokemonId = :pokemonId")
    suspend fun getAbilitiesForPokemon(pokemonId: Int): List<AbilityEntity>
}
