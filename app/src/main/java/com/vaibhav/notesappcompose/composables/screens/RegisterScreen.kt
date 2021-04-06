package com.vaibhav.notesappcompose.composables.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.composables.AuthTextField
import com.vaibhav.notesappcompose.composables.PrimaryButton
import com.vaibhav.notesappcompose.composables.UserAvatar
import com.vaibhav.notesappcompose.ui.theme.authTextBoxBgColor

@Composable
fun RegisterScreen(navController: NavController) {
    Surface(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Register To Continue", style = MaterialTheme.typography.h3)
            Spacer(modifier = Modifier.padding(16.dp))
            UserAvatar(image = R.drawable.avatar, size =150.dp)
            Spacer(modifier = Modifier.padding(16.dp))
            AuthTextField(
                label = "Username",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            AuthTextField(
                label = "Email",
                modifier = Modifier
                    .fillMaxWidth(),
                isPassword = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            AuthTextField(
                label = "Password",
                modifier = Modifier
                    .fillMaxWidth(),
                isPassword = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(text = "REGISTER",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}