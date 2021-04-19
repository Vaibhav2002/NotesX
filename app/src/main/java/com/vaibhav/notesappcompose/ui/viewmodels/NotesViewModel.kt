package com.vaibhav.notesappcompose.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.vaibhav.notesappcompose.data.models.entity.Note
import com.vaibhav.notesappcompose.data.repo.note.NoteRepoImpl
import com.vaibhav.notesappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteRepoImpl: NoteRepoImpl) : ViewModel() {

    val loadingState = mutableStateOf(false)
    val errorState = mutableStateOf("")
    val searchQuery = MutableLiveData("")
    val collectionId = MutableLiveData<Long>(0)
    val importantNotesCount = mutableStateOf(0L)
    val collectionName = mutableStateOf("")


    //delete note dialog
    val isDeleteDialogVisible = mutableStateOf(false)
    val deleteText = "Are you sure , you want to delete this collection?"
    private val deleteCNote = mutableStateOf<Note?>(null)

    private val _notes = searchQuery.switchMap {
        noteRepoImpl.getAllNotes(it)
    }
    val notes: LiveData<List<Note>> = _notes

    fun setCollectionName(name: String) {
        collectionName.value = name
    }

    fun dismissDialog() {
        isDeleteDialogVisible.value = false
    }

    fun showDialog(note: Note) {
        isDeleteDialogVisible.value = true
        deleteCNote.value = note
    }

    fun onDeleteDialogPressed() = viewModelScope.launch {
        dismissDialog()
        loadingState.value = true
        val resource = noteRepoImpl.deleteNote(deleteCNote.value ?: Note())
        when (resource) {
            is Resource.Error -> {
                loadingState.value = false
                errorState.value = resource.message.toString()
            }
            is Resource.Loading -> {

            }
            is Resource.Success -> {
                loadingState.value = false
            }
        }
    }


    fun setCollectionId(id: Long) {
        Timber.d(id.toString())
        collectionId.postValue(id)
        fetchNotes(id)
    }

    fun setImportantNotesCount(count: Long) {
        importantNotesCount.value = count
    }


    fun onQueryTextChange(query: String) {
        searchQuery.postValue(query)
    }

    private fun fetchNotes(id: Long) = viewModelScope.launch {
        loadingState.value = true
        val resource = noteRepoImpl.fetchNotes(id)
        Timber.d(resource.data.toString())
        when (resource) {
            is Resource.Loading -> {
            }
            is Resource.Success -> {
                loadingState.value = false
            }
            is Resource.Error -> {
                loadingState.value = false
                errorState.value = resource.message.toString()
                Timber.d(resource.message)
            }
        }

    }


}