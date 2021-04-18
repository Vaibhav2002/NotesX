package com.vaibhav.notesappcompose.data.local


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.vaibhav.notesappcompose.data.models.entity.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNotes(notes: List<Note>)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM note_table WHERE text LIKE :query ORDER BY isImportant DESC")
    fun getAllNotes(query: String): LiveData<List<Note>>
}