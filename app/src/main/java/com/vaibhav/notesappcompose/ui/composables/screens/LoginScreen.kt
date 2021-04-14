package com.vaibhav.notesappcompose.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate


@Composable
fun LoginScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Login To Continue",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(
            label = "Username",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            label = "Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            isPassword = true
        )
        Spacer(modifier = Modifier.height(32.dp))
        PrimaryButton(text = "LOGIN",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                navController.navigate("collectionScreen")
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        SwitchTOLoginOrRegisterTexts(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text1 = "Don't have an account yet?",
            text2 = "Register"
        ) {
            navController.navigate("registerScreen")
        }
    }
}







