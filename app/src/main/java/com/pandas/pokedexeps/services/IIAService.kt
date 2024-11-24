package com.pandas.pokedexeps.services

// Interfaz para definir los métodos del servicio IA
interface IIAService {
    suspend fun getFunFact(pokemonName: String): String
}