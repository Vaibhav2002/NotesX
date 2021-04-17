package com.vaibhav.notesappcompose.data.models.remote.responses

data class NoteResponseItem(
    val collectionId: Int,
    val id: Long,
    val isImportant: Boolean,
    val text: String,
    val timeStamp: String
)