package com.vaibhav.notesappcompose.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vaibhav.notesappcompose.data.models.entity.Note
import com.vaibhav.notesappcompose.data.repo.note.NoteRepoImpl
import com.vaibhav.notesappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val noteRepoImpl: NoteRepoImpl) : ViewModel() {

    val loadingState = mutableStateOf(false)
    val errorState = mutableStateOf("")
    val textState = mutableStateOf("")
    val isImportantState = mutableStateOf(false)
    val collectionId = mutableStateOf(0L)
    val navigateBackState = mutableStateOf(false)


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

    fun onAddNoteButtonPressed() {
        if (verifyText())
            addNote()
        else
            setErrorState("Please enter data correctly")
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