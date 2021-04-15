package com.vaibhav.notesappcompose.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaibhav.notesappcompose.data.models.entity.User
import com.vaibhav.notesappcompose.data.repo.auth.AuthRepoImpl
import com.vaibhav.notesappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepoImpl: AuthRepoImpl,
) : ViewModel() {

    val email = mutableStateOf("")
    val password = mutableStateOf("")

    private val _loginState = MutableLiveData<Resource<User>>()
    val loginState: LiveData<Resource<User>> = _loginState


    fun onEmailChange(e: String) {
        email.value = e
    }

    fun onPasswordChange(p: String) {
        password.value = p
    }

    fun onLoginButtonPressed() {
        if (validateInput())
            loginUser()
        else
            _loginState.postValue(Resource.Error("Please enter all field correctly"))

    }

    fun isUserLoggedIn() = authRepoImpl.isUserLoggedIn()

    private fun loginUser() = viewModelScope.launch {
        _loginState.postValue(Resource.Loading())
        _loginState.postValue(
            authRepoImpl.loginUser(
                email = email.value,
                password = password.value
            )
        )
    }

    private fun validateInput() =
        !(email.value.isEmpty() || email.value.isBlank() || password.value.isEmpty() || password.value.isBlank())


}