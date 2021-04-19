package com.vaibhav.notesappcompose.ui.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.composables.SecondaryButton
import com.vaibhav.notesappcompose.ui.composables.UserAvatar
import com.vaibhav.notesappcompose.ui.viewmodels.ProfileViewModel

@Composable
fun ProfileScreen(navController: NavController) {

    val viewModel: ProfileViewModel =
        hiltNavGraphViewModel(backStackEntry = navController.currentBackStackEntry!!)

    val name = viewModel.name.value
    val email = viewModel.email.value
    val navigateToLoginState = viewModel.navigateToLoginScreenState.value

    if (navigateToLoginState) {
        navController.navigate("loginScreen") {
            popUpTo("collectionScreen") { inclusive = true }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(32.dp))
        Text(
            text = "My Profile",
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground,
        )
        Spacer(modifier = Modifier.padding(32.dp))
        UserAvatar(image = R.drawable.avatar, size = 200.dp)
        Spacer(modifier = Modifier.padding(32.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = email,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.padding(32.dp))
        SecondaryButton(
            text = "LOG OUT",
            modifier = Modifier
                .fillMaxWidth()
                .clip(AbsoluteRoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            viewModel.onLogoutPressed()
        }
    }
}