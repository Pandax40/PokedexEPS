package com.pandas.pokedexeps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.pandas.pokedexeps.ui.navigation.CaptureZone
import com.pandas.pokedexeps.ui.home.HomeScreen
import com.pandas.pokedexeps.ui.capturezone.CaptureZoneScreen
import com.pandas.pokedexeps.ui.pokedex.PokedexScreen
import com.pandas.pokedexeps.ui.pokedex.detail.PokemonDetailScreen
import com.pandas.pokedexeps.ui.team.TeamScreen
import com.pandas.pokedexeps.ui.teamgeneration.TeamGenerationScreen
import com.pandas.pokedexeps.ui.theme.PokedexEPSTheme
import com.pandas.pokedexeps.ui.zonasCapturadas.ZonasCapturadasScreen
import com.pandas.pokedexeps.ui.navigation.Home
import com.pandas.pokedexeps.ui.navigation.Pokedex
import com.pandas.pokedexeps.ui.navigation.PokedexDetail
import com.pandas.pokedexeps.ui.navigation.Team
import com.pandas.pokedexeps.ui.navigation.TeamGeneration
import com.pandas.pokedexeps.ui.navigation.ZonasCapturadas

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexEPSTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(navController = navController, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavGraph(navController: androidx.navigation.NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Home, modifier = modifier) {
        composable<Home>{
            HomeScreen(navController = navController)
        }
        composable<Pokedex>{
            PokedexScreen(navController = navController)
        }
        composable<CaptureZone> {
            CaptureZoneScreen(navController = navController)
        }
        composable<PokedexDetail>   { blackStackEntry ->
            val pokemonId = blackStackEntry.toRoute<PokedexDetail>().pokemonId
            PokemonDetailScreen(pokemonId = pokemonId, navController = navController)
        }
        composable<Team> {
            TeamScreen(navController = navController)
        }

        composable<ZonasCapturadas> {
            ZonasCapturadasScreen(navController = navController)
        }
        composable<TeamGeneration> {
            TeamGenerationScreen(navController = navController)
        }
    }
}