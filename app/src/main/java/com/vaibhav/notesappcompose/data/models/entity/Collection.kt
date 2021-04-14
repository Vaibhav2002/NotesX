package com.vaibhav.notesappcompose.data.models.entity

data class Collection(
    val id: Int = 0,
    val name: String = "",
    val isImportant: Boolean = false,
    val timeStamp: String = System.currentTimeMillis().toString()
)
