package com.vaibhav.notesappcompose.data.repo.note

import androidx.lifecycle.LiveData
import com.vaibhav.notesappcompose.data.local.NoteDao
import com.vaibhav.notesappcompose.data.models.entity.Note
import com.vaibhav.notesappcompose.data.models.mappers.NoteMapper
import com.vaibhav.notesappcompose.data.models.remote.requests.NoteBody
import com.vaibhav.notesappcompose.data.remote.Api
import com.vaibhav.notesappcompose.data.util.mapToErrorResponse
import com.vaibhav.notesappcompose.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NoteRepoImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val api: Api,
    private val noteMapper: NoteMapper
) : NoteRepo {
    override fun getAllNotes(query: String): LiveData<List<Note>> =
        noteDao.getAllNotes(query = "$query%")

    override suspend fun addNote(collectionId: Long, note: Note): Resource<Note> =
        withContext(Dispatchers.IO) {
            try {
                val noteBody = NoteBody(
                    collectionId = collectionId,
                    text = note.text,
                    isImportant = note.isImportant,
                    timeStamp = note.timeStamp
                )

                val response = api.addNewNote(noteBody)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val noteEntity = noteMapper.mapToDomainModel(it)
                        saveNotesIntoDatabase(listOf(noteEntity))
                        return@withContext Resource.Success(noteEntity)
                    } ?: Resource.Error(response.message(), null)
                } else {
                    val error = mapToErrorResponse(response.errorBody())
                    return@withContext Resource.Error(
                        error.message
                    )
                }
            } catch (exception: Exception) {
                Resource.Error(exception.localizedMessage ?: "Oops something went wrong")
            }
        }

    override suspend fun fetchNotes(collectionId: Long): Resource<List<Note>> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.getAllNotes(collectionId = collectionId)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val entities = noteMapper.mapToDomainModelList(it)
                        deleteAllFromDatabase()
                        saveNotesIntoDatabase(entities)
                        return@withContext Resource.Success(entities)
                    } ?: Resource.Error(response.message())
                } else {
                    val error = mapToErrorResponse(response.errorBody())
                    return@withContext Resource.Error(
                        error.message
                    )
                }
            } catch (exception: Exception) {
                Resource.Error(exception.localizedMessage ?: "Oops something went wrong")
            }
        }

    override suspend fun saveNotesIntoDatabase(notes: List<Note>) = withContext(Dispatchers.IO) {
        noteDao.insertNotes(notes)
    }

    override suspend fun deleteAllFromDatabase() = withContext(Dispatchers.IO) {
        noteDao.deleteAllNotes()
    }
}