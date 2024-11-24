    package com.pandas.pokedexeps.ui.pokedex

    import androidx.activity.compose.BackHandler
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
    import androidx.compose.material3.Button
    import com.pandas.pokedexeps.ui.navigation.Home
    import com.pandas.pokedexeps.ui.navigation.PokedexDetail

    @Composable
    fun PokedexScreen(
        navController: NavController
    ) {
        val viewModel: PokedexViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
        val pokemonList by viewModel.pokemonList
        val pokemonOwnList by viewModel.pokemonOwnList
        val isLoading by viewModel.isLoading
        val sortingOption by viewModel.sortingOption

        BackHandler { navController.navigate(Home) }

        PokedexEPSTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .padding(top = 32.dp, bottom = 32.dp),
            ) {
                Text(
                    text = "Pokedex",
                    style = PokemonTypography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Botón único para cambiar las opciones de ordenamiento
                Button(
                    onClick = {
                        val newOption = if (sortingOption == SortingOption.BY_NAME) {
                            SortingOption.BY_ID
                        } else {
                            SortingOption.BY_NAME
                        }
                        viewModel.setSortingOption(newOption)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Orden: " + if (sortingOption == SortingOption.BY_NAME) "Nombre" else "ID"
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (isLoading) {
                    Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
                } else {
                    LazyColumn {
                        itemsIndexed(pokemonList.chunked(2)) { _, rowPokemons ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Start,
                            ) {
                                rowPokemons.forEach { pokemon ->
                                    val had = pokemonOwnList.contains(pokemon.id.toString())

                                    PokemonCard(
                                        id = pokemon.id,
                                        name = pokemon.name,
                                        imageUrl = pokemon.imageUrl,
                                        onClick = {
                                            navController.navigate(PokedexDetail(pokemon.id))
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