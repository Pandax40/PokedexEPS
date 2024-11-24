package com.pandas.pokedexeps.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade

@Composable
fun PokemonCard( id: Int,
                 name: String,
                 imageUrl: String,
                 onClick: () -> Unit,
                 had: Boolean = false,
                 modifier: Modifier = Modifier) {
    val backgroundColor = if (had) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surface
    }
    Card(
        modifier = modifier.padding(6.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = if (had) onClick else ({}),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier.padding(6.dp)
            ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if(had) Text(text = name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "#${id.toString()}", style = MaterialTheme.typography.bodyMedium)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Translated description of what the image contains",
                    modifier = Modifier.size(64.dp),
                    colorFilter = if(!had) ColorFilter.tint(Color.Gray.copy(alpha = 0.5f)) else null
                )
            }
        }
    }
}

@Preview
@Composable
fun PokemonCardPreview() {
    PokemonCard(id = 1, name = "Bulbasaur", onClick = {} , imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png")
}