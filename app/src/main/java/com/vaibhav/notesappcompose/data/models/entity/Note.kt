package com.vaibhav.notesappcompose.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "note_table")
data class Note(
    val text: String = "",
    val isImportant: Boolean = false,
    val timeStamp: String = System.currentTimeMillis().toString(),
    @PrimaryKey
    val id: Long = 0,
) : Serializable
