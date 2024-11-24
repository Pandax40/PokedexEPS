package com.pandas.pokedexeps.ui.pokedex

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pandas.pokedexeps.ui.theme.PokemonTypography
import com.pandas.pokedexeps.ui.components.PokemonCard
import com.pandas.pokedexeps.ui.theme.PokedexEPSTheme

@Composable
fun PokedexScreen(viewModel: PokedexViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), navController: NavController) {
    val pokemonList by viewModel.pokemonList
    val pokemonOwnList by viewModel.pokemonOwnList
    val isLoading by viewModel.isLoading

    PokedexEPSTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                text = "Pokedex",
                style = PokemonTypography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            if (isLoading) {
                Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn {
                    itemsIndexed(pokemonList.chunked(2)) { _, rowPokemons ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Absolute.Left,

                            ) {
                            rowPokemons.forEach { pokemon -> val had = if (pokemonOwnList.contains(pokemon.id.toString())) {
                                    true
                                } else {
                                    false
                                }

                                PokemonCard(
                                    id = pokemon.id,
                                    name = pokemon.name,
                                    imageUrl = pokemon.imageUrl,
                                    onClick = {
                                        navController.navigate("pokemon_detail_screen/${pokemon.id}")
                                    },
                                    had = had,
                                    modifier = Modifier.width(170.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
@Preview
@Composable
fun PokedexScreenPreview() {
    PokedexScreen(navController = rememberNavController())
}