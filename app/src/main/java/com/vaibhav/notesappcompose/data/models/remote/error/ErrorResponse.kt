package com.vaibhav.notesappcompose.data.models.remote.error

data class ErrorResponse(
    val error: String,
    val message: String,
    val path: String,
    val status: Int,
    val timestamp: String
)