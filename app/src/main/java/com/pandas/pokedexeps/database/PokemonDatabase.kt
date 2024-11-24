package com.pandas.pokedexeps.database



import androidx.room.Database
import androidx.room.RoomDatabase
import com.pandas.pokedexeps.models.*

@Database(
    entities = [
        PokemonEntity::class,
        AbilityEntity::class,
        TypeEntity::class,
        MoveEntity::class,
        StatEntity::class,
        EvolutionEntity::class,
        LocationEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    abstract fun abilityDao(): AbilityDao
    abstract fun typeDao(): TypeDao
    abstract fun moveDao(): MoveDao
    abstract fun statDao(): StatDao
    abstract fun evolutionDao(): EvolutionDao
    abstract fun locationDao(): LocationDao
}
