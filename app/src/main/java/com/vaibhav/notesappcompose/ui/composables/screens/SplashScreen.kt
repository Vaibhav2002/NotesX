package com.vaibhav.notesappcompose.ui.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.vaibhav.notesappcompose.ui.theme.blueDarkMode
import com.vaibhav.notesappcompose.ui.theme.blueLightDarkMode
import com.vaibhav.notesappcompose.ui.theme.bluePrimaryDark

@Composable
fun SplashScreen() {

    val colors = listOf(blueLightDarkMode, bluePrimaryDark, blueDarkMode)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colors))
    ) {

    }
}
