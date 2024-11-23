package com.pandas.pokedexeps.ui.pokedex

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pandas.pokedexeps.ui.theme.PokemonTypography
import com.pandas.pokedexeps.ui.components.PokemonCard

@Composable
fun PokedexScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 32.dp, bottom = 32.dp),
    ) {
        Text(
            text = "Pokedex",
            style = PokemonTypography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(10) { rowIndex ->
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    item {
                        PokemonCard(id = rowIndex * 2 + 1,
                            name = "Pokemon ${rowIndex * 2 + 1}",
                            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${rowIndex * 2 + 1}.png",
                            modifier = Modifier.width(180.dp))
                    }
                    item {
                        PokemonCard(id = rowIndex * 2 + 2,
                            name = "Pokemon ${rowIndex * 2 + 2}",
                            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${rowIndex * 2 + 2}.png",
                            modifier = Modifier.width(180.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PokedexScreenPreview() {
    PokedexScreen()
}