/*
package com.pandas.pokedexeps.ui.ia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pandas.pokedexeps.ui.theme.PokedexEPSTheme

@Composable
fun IaScreen(
    modifier: Modifier = Modifier,
    viewModel: iaViewModel = viewModel() // Obtenemos el ViewModel
) {
    var userInput by remember { mutableStateOf("") } // Estado para el texto del usuario
    val responseText by viewModel.responseText // Obtenemos la respuesta desde el ViewModel
    val isLoading by viewModel.isLoading // Obtenemos el estado de carga desde el ViewModel

    PokedexEPSTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Campo de texto para escribir
            BasicTextField(
                value = userInput,
                onValueChange = { userInput = it }, // Cambiar el estado cuando el usuario escribe
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray.copy(alpha = 0.1f))
                    .padding(16.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar el texto de respuesta o un cargando
            if (isLoading) {
                CircularProgressIndicator() // Indicador de carga mientras se genera la respuesta
            } else {
                Text(
                    text = responseText,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón "Enviar"
            Button(onClick = {
                // Llamamos al metodo del ViewModel para generar la respuesta
                viewModel.generarRespuesta(userInput)
            }) {
                Text(text = "Enviar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable*/
/**//*

fun IaScreenPreview() {
    IaScreen()
}
*/
