package com.vaibhav.notesappcompose.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vaibhav.notesappcompose.data.repo.auth.AuthRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authRepoImpl: AuthRepoImpl) : ViewModel() {


    val name = mutableStateOf("")
    val email = mutableStateOf("")
    val navigateToLoginScreenState = mutableStateOf(false)


    init {
        setUserData()
    }

    private fun setUserData() {
        val user = authRepoImpl.getUserData()
        name.value = user.username
        email.value = user.email
    }

    fun onLogoutPressed() {
        authRepoImpl.logoutUser()
        navigateToLoginScreenState.value = true
    }


}