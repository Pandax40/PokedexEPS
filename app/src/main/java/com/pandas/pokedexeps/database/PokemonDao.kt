package com.pandas.pokedexeps.database

import androidx.room.*
import com.pandas.pokedexeps.models.*

@Dao
interface PokemonDao {

    // Insert or update Pokémon
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAbilities(abilities: List<AbilityEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTypes(types: List<TypeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoves(moves: List<MoveEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStats(stats: List<StatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvolutions(evolutions: List<EvolutionEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<LocationEntity>)

    // Query Pokémon with all related data
    @Transaction
    @Query("SELECT * FROM pokemon_table WHERE id = :pokemonId")
    suspend fun getPokemonById(pokemonId: Int): PokemonWithDetails?

    // Query paginated Pokémon for lazy loading

    // Query paginated Pokémon for lazy loading
    @Transaction
    @Query("SELECT * FROM pokemon_table ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getPaginatedPokemon(limit: Int, offset: Int): List<PokemonWithDetails>
}
