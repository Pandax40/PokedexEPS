package com.pandas.pokedexeps.models.mappers


import com.pandas.pokedexeps.models.*

/**
 * Converts a domain model Pokemon to its corresponding Room entities.
 */
fun Pokemon.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        id = this.id,
        name = this.name,
        cries = this.cries,
        height = this.height,
        imageUrl = this.imageUrl,
        weight = this.weight,
        speciesName = this.species.name,
        speciesUrl = this.species.url
    )
}

fun List<Ability>.toAbilityEntities(pokemonId: Int): List<AbilityEntity> {
    return this.map {
        AbilityEntity(
            pokemonId = pokemonId,
            name = it.name,
            url = it.url,
            isHidden = it.isHidden,
            slot = it.slot
        )
    }
}

fun List<Type>.toTypeEntities(pokemonId: Int): List<TypeEntity> {
    return this.map {
        TypeEntity(
            pokemonId = pokemonId,
            name = it.name,
            url = it.url,
            slot = it.slot
        )
    }
}

fun List<Move>.toMoveEntities(pokemonId: Int): List<MoveEntity> {
    return this.map {
        MoveEntity(
            pokemonId = pokemonId,
            name = it.name,
            url = it.url
        )
    }
}

fun List<Stat>.toStatEntities(pokemonId: Int): List<StatEntity> {
    return this.map {
        StatEntity(
            pokemonId = pokemonId,
            name = it.name,
            url = it.url,
            baseStat = it.baseStat,
            effort = it.effort
        )
    }
}

fun Evolution?.toEvolutionEntity(pokemonId: Int): EvolutionEntity? {
    return this?.let {
        EvolutionEntity(
            id = it.id,
            pokemonId = pokemonId,
            name = it.name
        )
    }
}

fun List<String>.toLocationEntities(pokemonId: Int): List<LocationEntity> {
    return this.map {
        LocationEntity(
            pokemonId = pokemonId,
            location = it
        )
    }
}

/**
 * Converts a PokemonWithDetails entity into a domain model Pokemon.
 */
fun PokemonWithDetails.toPokemon(): Pokemon {
    return Pokemon(
        id = this.pokemon.id,
        name = this.pokemon.name,
        cries = this.pokemon.cries,
        height = this.pokemon.height,
        imageUrl = this.pokemon.imageUrl,
        weight = this.pokemon.weight,
        species = Species(
            name = this.pokemon.speciesName,
            url = this.pokemon.speciesUrl
        ),
        abilities = this.abilities.map { Ability(it.name, it.url, it.isHidden, it.slot) },
        types = this.types.map { Type(it.name, it.url, it.slot) },
        moves = this.moves.map { Move(it.name, it.url) },
        stats = this.stats.map { Stat(it.name, it.url, it.baseStat, it.effort) },
        evolvesTo = this.evolutions.firstOrNull()?.let {
            Evolution(it.name, it.id)
        },
        locations = this.locations.map { it.location }
    )
}
