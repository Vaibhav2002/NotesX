package com.vaibhav.notesappcompose.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaibhav.notesappcompose.data.models.entity.User
import com.vaibhav.notesappcompose.data.models.remote.requests.RegisterBody
import com.vaibhav.notesappcompose.data.repo.auth.AuthRepoImpl
import com.vaibhav.notesappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val authRepoImpl: AuthRepoImpl) : ViewModel() {
    val username = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")

    private val _loginState = MutableLiveData<Resource<User>>()
    val loginState: LiveData<Resource<User>> = _loginState


    fun onUserNameChange(u: String) {
        username.value = u
    }

    fun onEmailChange(e: String) {
        email.value = e
    }

    fun onPasswordChange(p: String) {
        password.value = p
    }

    fun onRegisterButtonPressed() {
        if (validateInput())
            registerUser()
        else
            _loginState.postValue(Resource.Error("Please enter all field correctly"))

    }

    private fun registerUser() = viewModelScope.launch {
        _loginState.postValue(Resource.Loading())
        _loginState.postValue(
            authRepoImpl.registerUser(
                RegisterBody(name = username.value, email = email.value, password = password.value)
            )
        )
    }

    private fun validateInput() =
        !(email.value.isEmpty() || email.value.isBlank() || password.value.isEmpty() || password.value.isBlank())


}