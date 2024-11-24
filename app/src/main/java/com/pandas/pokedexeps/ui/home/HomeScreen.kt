package com.pandas.pokedexeps.ui.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.pandas.pokedexeps.R
import com.pandas.pokedexeps.ui.navigation.CaptureZone
import com.pandas.pokedexeps.ui.navigation.Pokedex
import com.pandas.pokedexeps.ui.navigation.Team
import com.pandas.pokedexeps.ui.navigation.ZonasCapturadas
import com.pandas.pokedexeps.ui.theme.PokedexEPSTheme
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
    BackHandler {  navController.popBackStack(navController.graph.startDestinationId, inclusive = false) }
    PokedexEPSTheme {
        val navigateToCaptureZoneScreen by viewModel.navigateToCaptureZoneScreen
        val navigateToPokedexScreen by viewModel.navigateToPokedexScreen
        val navigateToTeamScreen by viewModel.navigateToTeamScreen
        val navigateToCapturedZones by viewModel.navigateToCapturedZones

        // Define un Animatable para el ángulo de rotación
        val rotationAngle = remember { Animatable(0f) }

        LaunchedEffect(navigateToPokedexScreen) {
            if (navigateToPokedexScreen) {
                navController.navigate(Pokedex)
                viewModel.onNavigatedToPokedexScreen()
            }
        }

        LaunchedEffect(navigateToCaptureZoneScreen) {
            if (navigateToCaptureZoneScreen) {
                navController.navigate(CaptureZone)
                viewModel.onNavigatedToCaptureZoneScreen()
            }
        }

        LaunchedEffect(navigateToTeamScreen) {
            if (navigateToTeamScreen) {
                navController.navigate(Team)
                viewModel.onNavigatedToTeamScreen()
            }
        }

        LaunchedEffect(navigateToCapturedZones) {
            if (navigateToCapturedZones) {
                navController.navigate(ZonasCapturadas)
                viewModel.onNavigatedToCapturedZones()
            }
        }

        // Inicia la animación cuando la composición entra en el Composition
        LaunchedEffect(Unit) {
            delay(200L)
            rotationAngle.animateTo(
                targetValue = 360f,
                animationSpec = tween(durationMillis = 1000) // Duración de 1 segundo
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokemon_logo),
                contentDescription = "Pokémon Logo",
                modifier = Modifier
                    .size(200.dp)
                    .graphicsLayer {
                        rotationZ = rotationAngle.value
                    }
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Welcome to the Pokémon App!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { viewModel.onPokedexButtonClicked() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Pokedex")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.onTeamButtonClicked() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Team")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.onCaptureZoneButtonClicked() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Capture Zone")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.onCapturedZonesButtonClicked() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Captured Zones")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}