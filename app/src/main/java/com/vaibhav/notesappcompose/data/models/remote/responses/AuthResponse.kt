package com.vaibhav.notesappcompose.data.models.remote.responses

data class AuthResponse(
    val email: String,
    val id: Int,
    val name: String,
    val password: String
)