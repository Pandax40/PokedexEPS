package com.pandas.pokedexeps.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = PokemonRed,
    secondary = PokemonBlue,
    tertiary = PokemonYellow,
    background = PokemonBlack,
    surface = PokemonBlack,
    onPrimary = PokemonWhite,
    onSecondary = PokemonWhite,
    onTertiary = PokemonWhite,
    onBackground = PokemonWhite,
    onSurface = PokemonWhite
)

private val LightColorScheme = lightColorScheme(
    primary = PokemonRed,
    secondary = PokemonBlue,
    tertiary = PokemonYellow,
    background = BackgroundColor,
    surface = BackgroundColor,
    onPrimary = PokemonWhite,
    onSecondary = PokemonWhite,
    onTertiary = PokemonWhite,
    onBackground = TextPrimaryColor,
    onSurface = TextPrimaryColor
)

@Composable
fun PokedexEPSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = PokemonTypography,
        content = content
    )
}