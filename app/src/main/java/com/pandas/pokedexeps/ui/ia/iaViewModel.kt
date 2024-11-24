package com.pandas.pokedexeps.ui.ia

import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel

class iaViewModel : ViewModel() {

    // Estado para manejar el texto de la respuesta
    private val _responseText = mutableStateOf("Escribe algo y presiona 'Enviar'")
    val responseText: State<String> = _responseText

    // Estado para manejar el cargando
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    // Metodo para simular la generación de la respuesta (como si fuera una llamada a una API)
    fun generarRespuesta(input: String) {
        _isLoading.value = true  // Activamos el estado de carga

        // Simulamos un retraso (como si fuera una llamada a una API)
        Handler(Looper.getMainLooper()).postDelayed({
            // Generamos una respuesta simulada
            _responseText.value = "Respuesta generada para: $input"
            _isLoading.value = false  // Desactivamos el estado de carga
        }, 2000)  // Retraso de 2 segundos (simulando tiempo de carga)
    }
}
