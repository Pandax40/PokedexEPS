package com.pandas.pokedexeps.services

object ServiceLocator {
    private const val BASE_URL_POKEMON = "https://hackeps-poke-backend.azurewebsites.net/"
    private const val BASE_URL_IA = "https://api-inference.huggingface.co/models/meta/llama-7b/"

    fun getBaseUrl(): String = BASE_URL_POKEMON
    fun getIAApiBaseUrl(): String = BASE_URL_IA
}
