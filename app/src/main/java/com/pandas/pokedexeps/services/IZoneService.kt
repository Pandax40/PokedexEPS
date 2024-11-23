package com.pandas.pokedexeps.services


import com.pandas.pokedexeps.models.Zone

interface IZoneService {
    suspend fun getZoneById(zoneId: String): Zone
}