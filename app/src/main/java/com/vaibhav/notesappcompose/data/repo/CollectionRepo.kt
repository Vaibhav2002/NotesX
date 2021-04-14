package com.vaibhav.notesappcompose.data.repo

import com.vaibhav.notesappcompose.data.models.entity.Collection
import com.vaibhav.notesappcompose.util.Resource

interface CollectionRepo {

    suspend fun addCollection(userId: String, collection: Collection): Resource<Collection>

    suspend fun getAllCollections(userId: String): Resource<List<Collection>>
}