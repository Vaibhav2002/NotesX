package com.vaibhav.notesappcompose.data.models.remote.requests

data class RegisterBody(
    val email: String,
    val name: String,
    val password: String
)