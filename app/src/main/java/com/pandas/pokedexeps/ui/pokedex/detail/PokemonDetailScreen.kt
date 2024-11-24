package com.pandas.pokedexeps.ui.pokedex.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pandas.pokedexeps.models.Pokemon
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

class PokemonDetailModelFactory(
    private val savedStateHandle: SavedStateHandle
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonDetailModel(savedStateHandle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


@Composable
fun PokemonDetailScreen(pokemonId: Int?,
                        navController: NavController) {
    val viewModel: PokemonDetailModel = viewModel(
        factory = PokemonDetailModelFactory(SavedStateHandle(mapOf("pokemonId" to pokemonId)))
    )
    val pokemonInfo by viewModel.pokemonDetail
    val isLoading by viewModel.isLoading

    if (isLoading) {
        Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
    } else {
        pokemonInfo?.let { pokemon ->
            PokemonDetailContent(pokemon)
        } ?: run {
            Text(text = "Pokemon not found", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun PokemonDetailContent(pokemon: Pokemon) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemon.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Image of ${pokemon.name}",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = pokemon.name, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "ID: ${pokemon.id}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Height: ${pokemon.height}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Weight: ${pokemon.weight}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Abilities:", style = MaterialTheme.typography.headlineSmall)
        pokemon.abilities.forEach { ability ->
            Text(text = "${ability.name} (Hidden: ${ability.isHidden}, Slot: ${ability.slot})", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Types:", style = MaterialTheme.typography.headlineSmall)
        pokemon.types.forEach { type ->
            Text(text = "${type.name} (Slot: ${type.slot})", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Stats:", style = MaterialTheme.typography.headlineSmall)
        pokemon.stats.forEach { stat ->
            Text(text = "${stat.name}: ${stat.baseStat} (Effort: ${stat.effort})", style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Moves:", style = MaterialTheme.typography.headlineSmall)
        pokemon.moves.forEach { move ->
            Text(text = move.name, style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Species: ${pokemon.species.name}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        pokemon.evolvesTo?.let { evolution ->
            Text(text = "Evolves To: ${evolution.name} (ID: ${evolution.id})", style = MaterialTheme.typography.bodyMedium)
        }
    }
}