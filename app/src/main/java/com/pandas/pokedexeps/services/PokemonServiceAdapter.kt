package com.pandas.pokedexeps.services

import com.pandas.pokedexeps.models.Ability
import com.pandas.pokedexeps.models.Evolution
import com.pandas.pokedexeps.models.Move
import com.pandas.pokedexeps.models.Pokemon
import com.pandas.pokedexeps.models.PokemonDTO
import com.pandas.pokedexeps.models.Species
import com.pandas.pokedexeps.models.Stat
import com.pandas.pokedexeps.models.Type


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
            abilities = dto.abilities.map {
                Ability(
                    name = it.ability.name,
                    url = it.ability.url,
                    isHidden = it.is_hidden,
                    slot = it.slot
                )
            },
            cries = dto.cries,
            height = dto.height,
            locations = dto.location_area_encounters,
            evolvesTo = dto.evolves_to?.let { Evolution(it.name, it.id) },
            moves = dto.moves.map { Move(it.name, it.url) },
            species = Species(dto.species.name, dto.species.url),
            imageUrl = dto.image,
            stats = dto.stats.map {
                Stat(
                    name = it.stat.name,
                    url = it.stat.url,
                    baseStat = it.base_stat,
                    effort = it.effort
                )
            },
            types = dto.types.map {
                Type(
                    name = it.type.name,
                    url = it.type.url,
                    slot = it.slot
                )
            },
            weight = dto.weight
        )
    }


    private interface PokemonApi {
        @GET("pokemons")
        suspend fun getAllPokemons(): List<PokemonDTO>

        @GET("pokemons/{id}")
        suspend fun getPokemonById(@Path("id") id: Int): PokemonDTO
    }
}
