package com.vaibhav.notesappcompose.ui.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.composables.SecondaryButton
import com.vaibhav.notesappcompose.ui.composables.UserAvatar

@Composable
fun ProfileScreen(navController: NavController) {
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
            text = "Vaibhav Jaiswal",
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "vaibhavsam2511@gmail.com",
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.padding(32.dp))
        SecondaryButton(
            text = "LOG OUT",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {

        }
    }
}