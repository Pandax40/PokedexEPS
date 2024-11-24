package com.pandas.pokedexeps.database

import android.content.Context
import androidx.room.Room

object PokemonDatabaseProvider {
    @Volatile
    private var INSTANCE: PokemonDatabase? = null

    fun getDatabase(context: Context): PokemonDatabase {
        return INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java,
                "pokemon_database"
            ).build().also { INSTANCE = it }
        }
    }
}
