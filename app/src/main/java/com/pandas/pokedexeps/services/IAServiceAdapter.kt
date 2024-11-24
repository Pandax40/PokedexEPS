package com.pandas.pokedexeps.services

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import com.pandas.pokedexeps.models.IAResponse
import java.util.concurrent.TimeUnit

class IAServiceAdapter : IIAService {

    private val api: IAApi

    init {
        api = initializeApiWithTimeout()
    }

    private fun initializeApiWithTimeout(): IAApi = runBlocking {
        try {
            withTimeout(5000) { // Timeout de 5 segundos
                val client = OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(ServiceLocator.getIAApiBaseUrl()) // Base URL de la API IA
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                return@withTimeout retrofit.create(IAApi::class.java)
            }
        } catch (e: TimeoutCancellationException) {
            Log.e("IAServiceAdapter", "Initialization timed out", e)
            throw e
        }
    }

    override suspend fun getFunFact(pokemonName: String): String = withContext(Dispatchers.IO) {
        val requestBody = mapOf(
            "prompt" to "Dame un dato curioso sobre el Pokémon $pokemonName.",
            "max_tokens" to 100,
            "temperature" to 0.7
        )
        return@withContext try {
            withTimeout(5000) { // Timeout de 5 segundos
                val response = api.getIAResponse(requestBody)
                Log.d("IAServiceAdapter", "Response: $response")
                val iaResponse = Gson().fromJson(response, IAResponse::class.java)
                iaResponse.choices.firstOrNull()?.text ?: "No se encontró un dato curioso."
            }
        } catch (e: Exception) {
            Log.e("IAServiceAdapter", "Error: ${e.message}", e)
            if (e is TimeoutCancellationException) {
                "Error: La solicitud ha tardado demasiado tiempo."
            } else {
                "Error al obtener el dato curioso: ${e.message}"
            }
        }
    }

    // Interfaz interna de Retrofit para definir los endpoints
    private interface IAApi {
        @Headers("Authorization: Bearer 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") // Reemplaza con tu clave de OpenAI
        @POST("v1/completions")
        suspend fun getIAResponse(@Body body: Map<String, Any>): String
    }
}