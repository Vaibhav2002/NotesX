package com.vaibhav.notesappcompose.ui.composables.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.composables.OutlinedTextField
import com.vaibhav.notesappcompose.ui.composables.PrimaryButton
import com.vaibhav.notesappcompose.ui.composables.SwitchTOLoginOrRegisterTexts
import com.vaibhav.notesappcompose.ui.composables.UserAvatar
import com.vaibhav.notesappcompose.ui.viewmodels.RegisterViewModel
import com.vaibhav.notesappcompose.util.Resource
import timber.log.Timber

@Composable
fun RegisterScreen(navController: NavController) {

    val viewModel: RegisterViewModel =
        hiltNavGraphViewModel(backStackEntry = navController.currentBackStackEntry!!)
    val uiState = viewModel.loginState.observeAsState()
    RegisterMainScreen(navController = navController, viewModel = viewModel)
    uiState.value?.let { state ->
        when (state) {
            is Resource.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            }
            is Resource.Success -> {
                Toast.makeText(
                    LocalContext.current,
                    "successfully registered",
                    Toast.LENGTH_SHORT
                )
                    .show()

                navController.navigate("collectionScreen") {
                    popUpTo("loginScreen") { inclusive = true }
                }
            }
            is Resource.Error -> {
                Toast.makeText(LocalContext.current, state.message, Toast.LENGTH_SHORT)
                    .show()
                Timber.d(state.message)
            }
        }
    }


}

@Composable
fun RegisterMainScreen(navController: NavController, viewModel: RegisterViewModel) {

    val username = viewModel.username.value
    val email = viewModel.email.value
    val password = viewModel.password.value


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
            value = username,
            label = "Username",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onValueChange = {
                viewModel.onUserNameChange(it)
            }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            label = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            isPassword = true,
            onValueChange = { viewModel.onEmailChange(it) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            label = "Password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            isPassword = true,
            onValueChange = { viewModel.onPasswordChange(it) }
        )
        Spacer(modifier = Modifier.height(32.dp))
        PrimaryButton(text = "REGISTER",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                viewModel.onRegisterButtonPressed()
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

