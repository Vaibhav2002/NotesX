package com.vaibhav.notesappcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = bluePrimaryDark,
    background= backgroundDark,
    primaryVariant = blueDarkMode,
    secondary = bluePrimaryDark,
    onPrimary = white,
    onBackground = white,
    onSecondary = white,
    surface = darkGray,
    onSurface = white
)

private val LightColorPalette = lightColors(
    primary = bluePrimary,
    primaryVariant = blueDark,
    secondary = bluePrimary,
    background = backgroundLight,
    onBackground = black,
    onPrimary =  white,
    surface = white,
    onSurface = black

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun NotesAppComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}