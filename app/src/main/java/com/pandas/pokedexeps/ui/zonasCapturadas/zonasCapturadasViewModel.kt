package com.pandas.pokedexeps.ui.zonasCapturadas

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class zonasCapturadasViewModel : ViewModel() {

    // Lista de zonas visitadas
    private val _zonasVisitadas = mutableStateOf(listOf(1, 3, 6)) // Ejemplo inicial
    val zonasVisitadas: State<List<Int>> = _zonasVisitadas

    // Estado del Switch de automatización
    private val _isAutomatizarOn = mutableStateOf(false) // Estado inicial del switch
    val isAutomatizarOn: State<Boolean> = _isAutomatizarOn

    // Metodo para alternar el estado del switch
    fun toggleAutomatizarCapturas(isOn: Boolean) {
        _isAutomatizarOn.value = isOn
    }

    // Metodo para agregar zonas (opcional)
    fun agregarZona(zona: Int) {
        _zonasVisitadas.value = _zonasVisitadas.value + zona
    }
}
