package com.vaibhav.notesappcompose.data.models.remote.requests

data class NoteBody(
    val collectionId: Long,
    val isImportant: Boolean,
    val text: String,
    val timeStamp: String
)