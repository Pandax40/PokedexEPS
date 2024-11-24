/*
package com.pandas.pokedexeps.ui.ia

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.pandas.pokedexeps.adapter.AdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.System.console

class iaViewModel : ViewModel() {

    // Estado para el texto de respuesta
    private val _responseText = mutableStateOf("Escribe el nombre de un Pokémon y presiona 'Enviar'")
    val responseText: State<String> = _responseText

    // Estado para manejar si está cargando
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val iaService = AdapterFactory.createIAServiceAdapter()


    fun generarRespuesta(pokemonName: String) {
        // Inicia el proceso de carga
        _isLoading.value = true

        // Llama al servicio de IA para obtener el dato curioso
        CoroutineScope(Dispatchers.Main).launch {
            try {
                if (pokemonName.isBlank()) {
                    throw IllegalArgumentException("El nombre del Pokémon no puede estar vacío.")
                }

                val response = iaService.getFunFact(pokemonName) // Llama a la API
                _responseText.value = response
            } catch (e: Exception) {
                // Captura y muestra el error
                _responseText.value = "Error: ${e.localizedMessage ?: "Algo salió mal"}"
            } finally {
                // Finaliza el estado de carga
                _isLoading.value = false
            }
        }
    }

}
*/
