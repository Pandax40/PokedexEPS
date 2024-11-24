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
import com.pandas.pokedexeps.ui.home.HomeScreen
import com.pandas.pokedexeps.ui.capturezone.CaptureZoneScreen
import com.pandas.pokedexeps.ui.components.PokemonCard
import com.pandas.pokedexeps.ui.components.PokemonCardPreview
import com.pandas.pokedexeps.ui.pokedex.PokedexScreen
import com.pandas.pokedexeps.ui.pokedex.detail.PokemonDetailScreen
import com.pandas.pokedexeps.ui.team.TeamScreen
import com.pandas.pokedexeps.ui.teamgeneration.TeamGenerationScreen
import com.pandas.pokedexeps.ui.theme.PokedexEPSTheme
import com.pandas.pokedexeps.ui.zonasCapturadas.ZonasCapturadasScreen

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
                //PokemonCard(id = 1, name = "Bulbasaur", imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
            }
        }
    }
}

@Composable
fun NavGraph(navController: androidx.navigation.NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home_screen", modifier = modifier) {
        composable("home_screen") {
            HomeScreen(navController = navController)
        }
        composable("pokedex_screen") {
            PokedexScreen(navController = navController)
        }
        composable("capture_zone_screen") {
            CaptureZoneScreen(navController = navController)
        }
        composable("pokemon_detail_screen/{pokemonId}") {blackStackEntry ->
            val pokemonId = blackStackEntry.arguments?.getString("pokemonId")?.toInt()
            PokemonDetailScreen(pokemonId = pokemonId, navController = navController)
        }
        composable("team_screen") {
            TeamScreen(navController = navController)
        }

        composable("zona_capturada_screen") {
            ZonasCapturadasScreen(navController = navController)
        }
        composable("team_generation"){
            TeamGenerationScreen(navController = navController)
        }
    }
}