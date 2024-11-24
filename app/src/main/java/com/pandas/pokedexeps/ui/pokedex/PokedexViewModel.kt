package com.pandas.pokedexeps.ui.pokedex

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pandas.pokedexeps.models.Pokemon
import com.pandas.pokedexeps.models.mappers.toPokemon
import com.pandas.pokedexeps.models.mappers.toPokemonEntity
import com.pandas.pokedexeps.database.PokemonDao
import com.pandas.pokedexeps.adapter.AdapterFactory
import com.pandas.pokedexeps.models.mappers.toAbilityEntities
import com.pandas.pokedexeps.models.mappers.toEvolutionEntity
import com.pandas.pokedexeps.models.mappers.toLocationEntities
import com.pandas.pokedexeps.models.mappers.toMoveEntities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.pandas.pokedexeps.models.mappers.toPokemon
import com.pandas.pokedexeps.models.mappers.toStatEntities
import com.pandas.pokedexeps.models.mappers.toTypeEntities

class PokedexViewModel(private val pokemonDao: PokemonDao) : ViewModel() {

    private val _pokemonList = mutableStateOf<List<Pokemon>>(emptyList())
    val pokemonList: State<List<Pokemon>> = _pokemonList

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        fetchPokemonData()
    }
    private suspend fun savePokemonWithDetails(remotePokemons: List<Pokemon>) {
        withContext(Dispatchers.IO) {
            // Inserta los datos principales y relacionados en Room
            remotePokemons.forEach { pokemon ->
                // Insertar el Pokémon principal
                pokemonDao.insertPokemon(pokemon.toPokemonEntity())

                // Insertar habilidades relacionadas
                pokemonDao.insertAbilities(pokemon.abilities.toAbilityEntities(pokemon.id))

                // Insertar tipos relacionados
                pokemonDao.insertTypes(pokemon.types.toTypeEntities(pokemon.id))

                // Insertar movimientos relacionados
                pokemonDao.insertMoves(pokemon.moves.toMoveEntities(pokemon.id))

                // Insertar estadísticas relacionadas
                pokemonDao.insertStats(pokemon.stats.toStatEntities(pokemon.id))

                // Insertar evolución relacionada (si existe)
                pokemon.evolvesTo?.toEvolutionEntity(pokemon.id)?.let {
                    pokemonDao.insertEvolutions(listOf(it))
                }

                // Insertar ubicaciones relacionadas
                pokemonDao.insertLocations(pokemon.locations.toLocationEntities(pokemon.id))
            }
        }
    }


    private fun fetchPokemonData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Check if Room has data
                val localPokemons = withContext(Dispatchers.IO) { pokemonDao.getPaginatedPokemon(limit = 20, offset = 0) }

                if (localPokemons.isNotEmpty()) {
                    // Load data from Room
                    _pokemonList.value = localPokemons.map { it.toPokemon() }

                    // Synchronize with API in the background
                    synchronizeWithApi()
                } else {
                    // Fetch from API if Room is empty
                    val remotePokemons = AdapterFactory.createPokemonServiceAdapter().getAllPokemons()

                    // Insert into Room
                    savePokemonWithDetails(remotePokemons)

                    // Update UI
                    _pokemonList.value = remotePokemons
                }
            } catch (e: Exception) {
                e.printStackTrace() // Handle errors
            } finally {
                _isLoading.value = false
            }
        }
    }


    private suspend fun synchronizeWithApi() {
        try {
            val remotePokemons = AdapterFactory.createPokemonServiceAdapter().getAllPokemons()
            val localPokemons = withContext(Dispatchers.IO) { pokemonDao.getPaginatedPokemon(limit = Int.MAX_VALUE, offset = 0) }

            val localIds = localPokemons.map { it.pokemon.id }.toSet()

            val remoteIds = remotePokemons.map { it.id }.toSet()

            // Detect new or updated Pokémon
            val newOrUpdated = remotePokemons.filter { it.id !in localIds || localPokemons.any { local -> local.pokemon.id == it.id && local.pokemon != it.toPokemonEntity() } }

            // Update Room
            if (newOrUpdated.isNotEmpty()) {

                savePokemonWithDetails(newOrUpdated)

                // If updated Pokémon are in the current list, update UI
                val updatedPokemonIds = newOrUpdated.map { it.id }
                _pokemonList.value = _pokemonList.value.map { if (it.id in updatedPokemonIds) newOrUpdated.first { updated -> updated.id == it.id } else it }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
