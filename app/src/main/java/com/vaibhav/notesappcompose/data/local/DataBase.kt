package com.vaibhav.notesappcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vaibhav.notesappcompose.data.models.entity.Collection
import com.vaibhav.notesappcompose.data.models.entity.Note

@Database(entities = [Collection::class, Note::class], version = 1, exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract val dao: Dao
}