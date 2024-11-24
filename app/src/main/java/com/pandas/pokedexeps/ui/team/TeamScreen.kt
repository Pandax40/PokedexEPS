package com.pandas.pokedexeps.ui.team

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pandas.pokedexeps.ui.pokedex.PokedexViewModel

@Composable
fun TeamScreen(viewModel: TeamViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), navController: NavController) {
    val team = viewModel.team.value
    val isLoading = viewModel.isLoading.value

    if (isLoading) {
        Text(text = "Loading...", style = MaterialTheme.typography.bodyLarge)
    } else {
        team?.let { team ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Team Name: ${team.name}",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "PVE Score: ${team.pveScore}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "PVP Score: ${team.pvpScore}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Pokedex Score: ${team.pokedexScore}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Number of Captured Pokémon: ${team.capturedPokemons.size}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Active: ${if (team.isActive) "Yes" else "No"}",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } ?: run {
            Text(text = "Team not found", style = MaterialTheme.typography.bodyLarge)
        }
    }
}