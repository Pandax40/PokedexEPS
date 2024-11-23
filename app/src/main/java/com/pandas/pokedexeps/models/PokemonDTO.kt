package com.pandas.pokedexeps.models

data class PokemonDTO(
    val id: Int,
    val name: String,
    val abilities: List<AbilityDTO>,
    val cries: String,
    val height: Int,
    val location_area_encounters: List<String>,
    val evolves_to: EvolutionDTO?,
    val moves: List<MoveDTO>,
    val species: SpeciesDTO,
    val image: String,
    val stats: List<StatDTO>,
    val types: List<TypeDTO>,
    val weight: Int
)

