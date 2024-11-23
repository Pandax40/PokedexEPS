package com.pandas.pokedexeps.services

object ServiceLocator {
    private const val BASE_URL = "https://hackeps-poke-backend.azurewebsites.net/"

    fun getBaseUrl(): String = BASE_URL
}
