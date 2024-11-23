package com.pandas.pokedexeps.services

import com.pandas.pokedexeps.models.Pokemon
import com.pandas.pokedexeps.models.PokemonDTO


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class PokemonServiceAdapter : IPokemonService {

    private val api: PokemonApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(ServiceLocator.getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(PokemonApi::class.java)
    }


    // Fetch all Pokémon
    override suspend fun getAllPokemons(): List<Pokemon> = withContext(Dispatchers.IO) {
        val pokemonDTOs = api.getAllPokemons()
        pokemonDTOs.map { dto ->
            mapPokemonDTOToModel(dto)
        }
    }

    // Fetch a single Pokémon by ID
    override suspend fun getPokemonById(id: Int): Pokemon = withContext(Dispatchers.IO) {
        val pokemonDTO = api.getPokemonById(id)
        mapPokemonDTOToModel(pokemonDTO)
    }

    // Helper function to map DTO to model
    private fun mapPokemonDTOToModel(dto: PokemonDTO): Pokemon {
        return Pokemon(
            id = dto.id,
            name = dto.name,
            imageUrl = dto.image,
            types = dto.types.map { it.type.name }
        )
    }

    private interface PokemonApi {
        @GET("pokemons")
        suspend fun getAllPokemons(): List<PokemonDTO>

        @GET("pokemons/{id}")
        suspend fun getPokemonById(@Path("id") id: Int): PokemonDTO
    }
}
