package com.vaibhav.notesappcompose.ui.composables.screens

import android.os.Handler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.theme.blueDarkMode
import com.vaibhav.notesappcompose.ui.theme.blueLightDarkMode
import com.vaibhav.notesappcompose.ui.theme.bluePrimaryDark
import com.vaibhav.notesappcompose.ui.viewmodels.SplashViewModel

@Composable
fun SplashScreen(navController: NavController) {

    val viewModel: SplashViewModel =
        hiltNavGraphViewModel(backStackEntry = navController.currentBackStackEntry!!)

    Handler().postDelayed({
        if (viewModel.isUserLoggedIn)
            navController.navigate("collectionScreen") {
                popUpTo("splashScreen") { inclusive = true }
            }
        else
            navController.navigate("loginScreen") {
                popUpTo("splashScreen") { inclusive = true }
            }
    }, 2000L)


    val colors = listOf(blueLightDarkMode, bluePrimaryDark, blueDarkMode)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colors)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(200.dp)
                .width(200.dp)
        )
    }
}
