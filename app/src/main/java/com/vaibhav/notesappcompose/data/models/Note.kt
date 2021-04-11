package com.vaibhav.notesappcompose.data.models

data class Note(
    val text:String,
    val isImportant:Boolean,
    val timeStamp:String = System.currentTimeMillis().toString(),
)
