package com.pandas.pokedexeps.ui.teamgeneration


import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pandas.pokedexeps.ui.components.PokemonCard
import com.pandas.pokedexeps.ui.navigation.Home
import com.pandas.pokedexeps.ui.theme.PokedexEPSTheme

@Composable
fun TeamGenerationScreen(navController: NavController) {
    val viewModel: TeamGenerationViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    val teamList by viewModel.teamList
    val isLoading by viewModel.isLoading
    BackHandler {
        navController.navigate(Home)
    }
    PokedexEPSTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Team Generation",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Button(
                onClick = { viewModel.generateBestTeam() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (isLoading) "Generating..." else "Generate Best Team")
            }
            Spacer(modifier = Modifier.height(16.dp))

            if (teamList.isNotEmpty()) {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(teamList) { pokemon ->
                        PokemonCard(
                            id = pokemon.id,
                            name = pokemon.name,
                            imageUrl = pokemon.imageUrl,
                            onClick = {
                                navController.navigate("pokemon_detail_screen/${pokemon.id}")
                            },
                            had = true,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            } else if (!isLoading) {
                Text(text = "No team generated yet.", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TeamGenerationScreenPreview() {
    TeamGenerationScreen(navController = rememberNavController())
}
