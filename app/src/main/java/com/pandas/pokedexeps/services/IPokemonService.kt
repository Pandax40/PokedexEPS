package  com.pandas.pokedexeps.services
import com.pandas.pokedexeps.models.Pokemon

interface IPokemonService {
    suspend fun getAllPokemons(): List<Pokemon>
    suspend fun getPokemonById(id: Int): Pokemon
}