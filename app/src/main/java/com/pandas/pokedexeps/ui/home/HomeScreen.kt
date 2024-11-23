package com.pandas.pokedexeps.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pandas.pokedexeps.R
import com.pandas.pokedexeps.ui.theme.PokedexEPSTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
) {
    PokedexEPSTheme {
        val navigateToCaptureZoneScreen by viewModel.navigateToCaptureZoneScreen

        LaunchedEffect(navigateToCaptureZoneScreen) {
            if (navigateToCaptureZoneScreen) {
                navController.navigate("capture_zone_screen")
                viewModel.onNavigatedToCaptureZoneScreen()
            }
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokemon_logo),
                contentDescription = "Pokémon Logo",
                modifier = Modifier.size(200.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Welcome to the Pokémon App!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { /* Navigate to Pokedex */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Pokedex")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Navigate to Create Team */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Team")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* Navigate to View Teams */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ver Zonas")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.onCaptureZoneButtonClicked() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Capturar Zona")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}