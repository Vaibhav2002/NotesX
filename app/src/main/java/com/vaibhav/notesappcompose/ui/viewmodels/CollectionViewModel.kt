package com.vaibhav.notesappcompose.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.vaibhav.notesappcompose.data.models.entity.Collection
import com.vaibhav.notesappcompose.data.models.entity.User
import com.vaibhav.notesappcompose.data.repo.collection.CollectionRepoImpl
import com.vaibhav.notesappcompose.data.repo.preferences.PreferencesRepo
import com.vaibhav.notesappcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(
    private val collectionRepoImpl: CollectionRepoImpl,
    private val preferencesRepo: PreferencesRepo
) : ViewModel() {


    private val user = preferencesRepo.getUserData()

    val errorState = mutableStateOf("")
    val loadingState = mutableStateOf(false)
    val dialogState = mutableStateOf(false)
    val queryState = MutableLiveData<String>("")


//    val collections = collectionRepoImpl.getAllCollections(queryState.value)


    val collections = queryState.switchMap {
        collectionRepoImpl.getAllCollections(it)
    }

    //addCollectionScreen
    var collectionName = mutableStateOf("")
    var isCollectionImportant = mutableStateOf(false)
    var addCollectionLoadingState = mutableStateOf(false)

    fun setDialogState(state: Boolean) {
        dialogState.value = state
    }

    fun onCollectionNameChange(name: String) {
        collectionName.value = name
    }

    fun onCollectionIsImportantChange(isImportant: Boolean) {
        isCollectionImportant.value = isImportant
    }

    fun onQueryTextChange(query: String) {
        queryState.postValue(query)
    }

    init {
        user?.let {
            val list = collections.value ?: emptyList<Collection>()
            if (list.isEmpty())
                loadingState.value = true
            Timber.d(it.toString())
            fetchNewData(it)
        }
    }

    fun onSaveCollectionPressed() = addNewCollection()


    fun closeDialog() {
        resetAddCollectionData()
        dialogState.value = false
    }

    private fun resetAddCollectionData() {
        isCollectionImportant.value = false
        collectionName.value = ""
    }


    private fun fetchNewData(user: User) = viewModelScope.launch {
        val resource = collectionRepoImpl.fetchCollections(user.id.toLong())
        when (resource) {
            is Resource.Loading -> {
            }
            is Resource.Success -> {
                loadingState.value = false
            }
            is Resource.Error -> {
                loadingState.value = false
                errorState.value = resource.message.toString()
            }
        }
    }


    private fun addNewCollection() = viewModelScope.launch {
        addCollectionLoadingState.value = true

        val collection = Collection(
            name = collectionName.value,
            isImportant = isCollectionImportant.value,
            timeStamp = System.currentTimeMillis().toString()
        )
        Timber.d("Collection From ViewModel $collection")
        val resource =
            collectionRepoImpl.addCollection(
                user?.id ?: 0,
                collection
            )
        when (resource) {
            is Resource.Loading -> {
            }
            is Resource.Success -> {
                addCollectionLoadingState.value = false
                closeDialog()
            }
            is Resource.Error -> {
                addCollectionLoadingState.value = false
                errorState.value = resource.message.toString()
            }
        }
    }


}