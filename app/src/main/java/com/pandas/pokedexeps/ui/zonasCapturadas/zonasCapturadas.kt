package com.pandas.pokedexeps.ui.zonasCapturadas

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pandas.pokedexeps.R
import com.pandas.pokedexeps.ui.theme.PokedexEPSTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.pandas.pokedexeps.ui.navigation.Home

@Composable
fun ZonasCapturadasScreen(
    navController: NavHostController
) {
    val viewModel: ZonasCapturadasViewModel = viewModel()
    // Obtenemos la lista de zonas visitadas desde el ViewModel
    val zonasCapturadas by viewModel.zonasCapturadasMap // Observamos las zonas visitadas

    // Obtenemos el estado del switch desde el ViewModel
    val isAutomatizarOn by viewModel.isAutomatizarOn // Observamos el estado del switch
    val showCooldownPopup by viewModel.showCooldownPopup
    BackHandler {
        navController.navigate(Home)
    }

    PokedexEPSTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.zonascapturadas_imagen),
                contentDescription = "Imagen de Zonas Capturadas",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Zonas Visitadas",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(24.dp))

            // Switch de automatización
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = if (isAutomatizarOn) "Automatización: ON" else "Automatización: OFF",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Switch(
                    checked = isAutomatizarOn,
                    onCheckedChange = { viewModel.toggleAutomatizarCapturas(it) },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        uncheckedThumbColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de zonas visitadas
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(zonasCapturadas.values.toList()) { zonaKey ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Zona $zonaKey",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        // Botón "Capturar"
                        if (!isAutomatizarOn) {
                            Button(onClick = { viewModel.attemptCapture(zonaKey.toString()) }) {
                                Text(text = "Capturar")
                            }
                        }
                    }
                }
            }
        }

        // Pop-up de cooldown
        if (showCooldownPopup) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.showCooldownPopup.value = false
                },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.showCooldownPopup.value = false
                    }) {
                        Text("OK")
                    }
                },
                title = {
                    Text("Cooldown Activo")
                },
                text = {
                    Text("No puedes cazar Pokémon en esa área porque tienes un cooldown.")
                }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ZonasCapturadasPreview() {
    ZonasCapturadasScreen(navController = rememberNavController())
}
