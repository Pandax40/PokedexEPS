package com.pandas.pokedexeps.models

data class ZoneDTO(
    val id: String,
    val name: String,
    val cooldown_period: Int,
    val last_requests_by_team: List<LastRequestByTeamDTO>
)

