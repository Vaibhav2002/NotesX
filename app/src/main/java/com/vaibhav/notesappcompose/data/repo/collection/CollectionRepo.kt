package com.vaibhav.notesappcompose.data.repo.collection

import androidx.lifecycle.LiveData
import com.vaibhav.notesappcompose.data.models.entity.Collection
import com.vaibhav.notesappcompose.util.Resource

interface CollectionRepo {

    fun getAllCollections(query: String): LiveData<List<Collection>>

    suspend fun addCollection(userId: Int, collection: Collection): Resource<Collection>

    suspend fun fetchCollections(userId: Long): Resource<List<Collection>>

    suspend fun saveCollectionIntoDatabase(collection: List<Collection>)

    suspend fun deleteAllFromDatabase()
}