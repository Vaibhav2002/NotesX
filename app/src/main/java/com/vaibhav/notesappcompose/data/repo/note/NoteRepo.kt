package com.vaibhav.notesappcompose.data.repo.note

import androidx.lifecycle.LiveData
import com.vaibhav.notesappcompose.data.models.entity.Note
import com.vaibhav.notesappcompose.util.Resource

interface NoteRepo {

    fun getAllNotes(query: String): LiveData<List<Note>>

    suspend fun addNote(collectionId: Long, note: Note): Resource<Note>

    suspend fun fetchNotes(collectionId: Long): Resource<List<Note>>

    suspend fun saveNotesIntoDatabase(notes: List<Note>)

    suspend fun deleteAllFromDatabase()

    suspend fun deleteNote(note: Note): Resource<Note>

    suspend fun deleteNoteFromDb(note: Note)
}