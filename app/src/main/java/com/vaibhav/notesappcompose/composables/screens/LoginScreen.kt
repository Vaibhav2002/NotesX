package com.vaibhav.notesappcompose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import com.google.android.material.color.MaterialColors
import com.vaibhav.notesappcompose.ui.theme.authTextBoxBgColor



@Composable
fun LoginScreen(navController: NavController) {

    Surface(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Login To Continue", style = MaterialTheme.typography.h3)
            Spacer(modifier = Modifier.padding(16.dp))
            AuthTextField(
                label = "Username",
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            AuthTextField(
                label = "Password",
                modifier = Modifier
                    .fillMaxWidth(),
                isPassword = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButton(text = "LOGIN",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate("register")
                }
            )
        }
    }

}





