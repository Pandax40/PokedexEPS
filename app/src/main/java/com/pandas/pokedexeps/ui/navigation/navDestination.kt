package com.pandas.pokedexeps.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object Pokedex

@Serializable
data class PokedexDetail(val pokemonId: Int)

@Serializable
object CaptureZone

@Serializable
object Team

@Serializable
object ZonasCapturadas
