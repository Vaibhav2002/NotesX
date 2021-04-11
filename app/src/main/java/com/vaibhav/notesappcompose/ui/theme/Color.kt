package com.vaibhav.notesappcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

//light color scheme
val backgroundLight = Color(0xFFF6F5F5)
val bluePrimary = Color(0xFF1687A7)
val blueDark = Color(0xFF276678)
val blueLight = Color(0xFFD3E0EA)

//dark color scheme
val backgroundDark = Color(0xFF1b262c)
val bluePrimaryDark = Color(0xFF3282b8)
val blueDarkMode = Color(0xFF0f4c75)
val blueLightDarkMode = Color(0xFFbbe1fa)


val black = Color(0xFF000000)
val darkGray = Color(0xFF595858)
val lightGray = Color(0xFFAAAAAA)
val white = Color(0xFFFFFFFF)

val authTextBoxBgColor = Color(0xFFFFFFFF)


@Composable
fun getColorForCards(isImportant: Boolean) = if (isImportant) {
    if (isSystemInDarkTheme())
        blueDarkMode
    else
        blueLight
} else MaterialTheme.colors.surface

