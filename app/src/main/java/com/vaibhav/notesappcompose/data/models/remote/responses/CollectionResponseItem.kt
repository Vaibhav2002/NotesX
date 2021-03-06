package com.vaibhav.notesappcompose.data.models.remote.responses

data class CollectionResponseItem(
    val id: Long,
    val isImportant: Boolean,
    val name: String,
    val timeStamp: String,
    val userId: Int
)