package com.vaibhav.notesappcompose.data.models.remote.requests

data class CollectionBody(
    val isImportant: Boolean,
    val name: String,
    val timestamp: String,
    val userId: Int
)