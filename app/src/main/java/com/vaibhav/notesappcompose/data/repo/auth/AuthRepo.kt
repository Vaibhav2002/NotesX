package com.vaibhav.notesappcompose.data.repo.auth

import com.vaibhav.notesappcompose.data.models.entity.User
import com.vaibhav.notesappcompose.data.models.remote.requests.RegisterBody
import com.vaibhav.notesappcompose.util.Resource

interface AuthRepo {

    suspend fun loginUser(email: String, password: String): Resource<User>

    suspend fun registerUser(registerBody: RegisterBody): Resource<User>

    suspend fun logoutUser()

    fun getUserData(): User
}