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
    val searchQuery = MutableLiveData<String>("")
    val collectionId = MutableLiveData<Long>(0)

    private val _notes = searchQuery.switchMap {
        noteRepoImpl.getAllNotes(it)
    }
    val notes: LiveData<List<Note>> = _notes

    fun setCollectionId(id: Long) {
        collectionId.postValue(id)
        fetchNotes()
    }


    fun onQueryTextChange(query: String) {
        searchQuery.postValue(query)
    }

    private fun fetchNotes() = viewModelScope.launch {
        loadingState.value = true
        val resource = noteRepoImpl.fetchNotes(collectionId.value ?: 0)
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