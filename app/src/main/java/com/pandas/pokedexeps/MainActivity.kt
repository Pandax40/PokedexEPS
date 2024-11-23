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
import com.pandas.pokedexeps.ui.theme.PokedexEPSTheme

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
    NavHost(navController = navController, startDestination = "home_screen", modifier = modifier) {
        composable("home_screen") {
            HomeScreen(navController = navController)
        }
        composable("capture_zone_screen") {
            CaptureZoneScreen(navController = navController)
        }
    }
}