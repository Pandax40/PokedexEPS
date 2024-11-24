package com.pandas.pokedexeps.algorithm

import com.pandas.pokedexeps.models.Pokemon
import kotlin.random.Random

 class GeneticAlgorithm(private val pokemons: List<Pokemon>) : AlgorithmTeamGeneration {
    private val populationSize = 100
    private val eliteCount = 20
    private val generations = 20
    private val mutationRate = 0.1

    override fun findBestTeam(): List<Pokemon> {
        var population = generateInitialPopulation()

        repeat(generations) {
            val rankedPopulation = population.map { it to evaluateFitness(it) }.sortedByDescending { it.second }
            val elite = rankedPopulation.take(eliteCount).map { it.first }

            val newPopulation = mutableListOf<List<Pokemon>>()
            newPopulation.addAll(elite)

            while (newPopulation.size < populationSize) {
                val parent1 = elite.random()
                val parent2 = elite.random()
                val offspring = crossover(parent1, parent2)
                newPopulation.add(offspring)
            }

            population = newPopulation.map { mutate(it) }
        }

        return population.maxByOrNull { evaluateFitness(it) }!!
    }

    private fun generateInitialPopulation(): List<List<Pokemon>> {
        return List(populationSize) { pokemons.shuffled().take(6) }
    }

    private fun evaluateFitness(team: List<Pokemon>): Int {
        val typeDiversity = team.flatMap { it.types.map { type -> type.name } }.distinct().size
        val totalStats = team.sumOf { it.stats.sumOf { stat -> stat.baseStat } }
        return typeDiversity * 10 + totalStats
    }

    private fun crossover(parent1: List<Pokemon>, parent2: List<Pokemon>): List<Pokemon> {
        val split = Random.nextInt(parent1.size)
        return (parent1.take(split) + parent2.takeLast(parent2.size - split)).distinct().take(6)
    }

    private fun mutate(team: List<Pokemon>): List<Pokemon> {
        if (Random.nextDouble() > mutationRate) return team
        val mutatedTeam = team.toMutableList()
        val indexToReplace = Random.nextInt(team.size)
        mutatedTeam[indexToReplace] = pokemons.random()
        return mutatedTeam
    }
}