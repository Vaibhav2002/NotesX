package com.vaibhav.notesappcompose.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.vaibhav.notesappcompose.data.models.entity.Note
import com.vaibhav.notesappcompose.data.repo.note.NoteRepoImpl
import com.vaibhav.notesappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditNoteViewModel @Inject constructor(private val noteRepoImpl: NoteRepoImpl) :
    ViewModel() {

    val loadingState = mutableStateOf(false)
    val errorState = mutableStateOf("")
    val textState = mutableStateOf("")
    val isImportantState = mutableStateOf(false)
    val collectionId = mutableStateOf(0L)
    val navigateBackState = mutableStateOf(false)
    val topText = mutableStateOf("Create\nNote")
    val note = mutableStateOf<Note?>(null)


    fun setNote(noteVal: String) {
        val n = Gson().fromJson(noteVal, Note::class.java)
        note.value = n
        textState.value = n.text
        isImportantState.value = n.isImportant
        topText.value = "Edit\nNote"
    }

    private fun setLoadingState(state: Boolean) {
        loadingState.value = state
    }

    private fun setErrorState(error: String) {
        errorState.value = error
    }

    fun onTextChange(text: String) {
        textState.value = text
    }

    fun setCollectionId(id: Long) {
        collectionId.value = id
    }

    fun setIsImportantState(state: Boolean) {
        isImportantState.value = state
    }

    fun onFabPressed() {
        if (verifyText()) {
            note.value?.let {
                editNote(it)
            } ?: addNote()
        } else
            setErrorState("Please enter data correctly")
    }

    private fun editNote(note: Note) = viewModelScope.launch {
        setLoadingState(true)
        val note = Note(
            id = note.id,
            text = textState.value,
            isImportant = isImportantState.value
        )
        val resource = noteRepoImpl.updateNote(note)
        when (resource) {
            is Resource.Loading -> {
            }
            is Resource.Success -> {
                setLoadingState(false)
                navigateBackState.value = true
            }
            is Resource.Error -> {
                setLoadingState(false)
                setErrorState(resource.message ?: "Oops something went wrong")
            }
        }
    }


    private fun addNote() = viewModelScope.launch {
        setLoadingState(true)
        val note = Note(
            text = textState.value,
            isImportant = isImportantState.value,
        )
        val resource = noteRepoImpl.addNote(collectionId.value, note)
        when (resource) {
            is Resource.Loading -> {
            }
            is Resource.Success -> {
                setLoadingState(false)
                navigateBackState.value = true
            }
            is Resource.Error -> {
                setLoadingState(false)
                setErrorState(resource.message ?: "Oops something went wrong")
            }
        }
    }

    private fun verifyText() = textState.value.isNotBlank() && textState.value.isNotEmpty()
}