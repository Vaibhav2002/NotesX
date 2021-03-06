package com.vaibhav.notesappcompose.data.models.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collection_table")
data class Collection(
    @PrimaryKey
    val id: Long = 0,
    val name: String = "",
    val isImportant: Boolean = false,
    val timeStamp: String = System.currentTimeMillis().toString()
)
