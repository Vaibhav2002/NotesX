package com.vaibhav.notesappcompose.ui.composables.screens

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
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.composables.OutlinedTextField
import com.vaibhav.notesappcompose.ui.composables.PrimaryButton
import com.vaibhav.notesappcompose.ui.composables.SwitchTOLoginOrRegisterTexts
import com.vaibhav.notesappcompose.ui.composables.UserAvatar

@Composable
fun RegisterScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Register To Continue",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        UserAvatar(image = R.drawable.avatar, size = 150.dp)
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(
            label = "Username",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            label = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            isPassword = true
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
        PrimaryButton(text = "REGISTER",
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
            text1 = "Already have an account?",
            text2 = "Login"
        ) {
            navController.popBackStack()
        }
    }
}
