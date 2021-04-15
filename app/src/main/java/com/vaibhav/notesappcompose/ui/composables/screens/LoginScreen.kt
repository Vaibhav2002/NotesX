package com.vaibhav.notesappcompose.ui.composables

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
import com.vaibhav.notesappcompose.ui.viewmodels.LoginViewModel
import com.vaibhav.notesappcompose.util.Resource
import timber.log.Timber


@Composable
fun LoginScreen(navController: NavController) {

    val viewModel: LoginViewModel =
        hiltNavGraphViewModel(backStackEntry = navController.currentBackStackEntry!!)

    if (viewModel.isUserLoggedIn())
        navigateToCollectionScreen(navController)

    val uiState = viewModel.loginState.observeAsState()
    LoginMainScreen(navController = navController, viewModel = viewModel)
    uiState.value?.let { state ->
        when (state) {
            is Resource.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is Resource.Success -> {
                Toast.makeText(LocalContext.current, "Successfully Logged In", Toast.LENGTH_SHORT)
                    .show()
                navigateToCollectionScreen(navController)
            }
            is Resource.Error -> {
                Toast.makeText(LocalContext.current, state.message, Toast.LENGTH_SHORT)
                    .show()
                Timber.d(state.message)
            }
        }
    }


}


fun navigateToCollectionScreen(navController: NavController) {
    navController.navigate("collectionScreen") {
        popUpTo("loginScreen") { inclusive = true }
    }
}

@Composable
fun LoginMainScreen(navController: NavController, viewModel: LoginViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val email = viewModel.email.value
        val password = viewModel.password.value

        Text(
            text = "Login To Continue",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(
            value = email,
            label = "Email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
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
        PrimaryButton(text = "LOGIN",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            onClick = {
                viewModel.onLoginButtonPressed()
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






