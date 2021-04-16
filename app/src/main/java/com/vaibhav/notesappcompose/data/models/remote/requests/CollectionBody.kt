package com.vaibhav.notesappcompose.data.models.remote.requests

data class CollectionBody(
    val isImportant: Boolean,
    val name: String,
    val timeStamp: String,
    val userId: Int
)