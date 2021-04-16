package com.vaibhav.notesappcompose.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(
    val text: String,
    val isImportant: Boolean,
    val timeStamp: String = System.currentTimeMillis().toString(),
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
)
