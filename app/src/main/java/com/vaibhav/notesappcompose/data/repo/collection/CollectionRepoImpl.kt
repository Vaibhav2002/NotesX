package com.vaibhav.notesappcompose.data.repo.collection

import androidx.lifecycle.LiveData
import com.vaibhav.notesappcompose.data.local.Dao
import com.vaibhav.notesappcompose.data.models.entity.Collection
import com.vaibhav.notesappcompose.data.models.mappers.CollectionMapper
import com.vaibhav.notesappcompose.data.models.remote.requests.CollectionBody
import com.vaibhav.notesappcompose.data.remote.Api
import com.vaibhav.notesappcompose.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CollectionRepoImpl @Inject constructor(
    private val dao: Dao,
    private val api: Api,
    private val collectionMapper: CollectionMapper
) : CollectionRepo {

    override fun getAllCollections(query: String): LiveData<List<Collection>> =
            dao.getAllCollectionsBasedOnQuery("$query%")

    override suspend fun addCollection(
        userId: Int,
        collection: Collection
    ): Resource<Collection> = withContext(Dispatchers.IO) {

        val collectionBody = CollectionBody(
            isImportant = collection.isImportant,
            name = collection.name,
            timeStamp = collection.timeStamp,
            userId = userId
        )
        val response = api.addCollection(
            collectionBody
        )
        Timber.d("Collection From Repo $collectionBody")
        if (response.isSuccessful) {
            response.body()?.let {
                saveCollectionIntoDatabase(listOf(collection))
                Resource.Success(collection)
            } ?: Resource.Error(response.message(), null)
        } else
            Resource.Error(response.message(), null)
    }


    override suspend fun fetchCollections(userId: Long): Resource<List<Collection>> =
        withContext(Dispatchers.IO) {
            val response = api.getAllCollections(userId)
            if (response.isSuccessful) {
                response.body()?.let {
                    deleteAllFromDatabase()
                    val collections = collectionMapper.mapToDomainModelList(it)
                    saveCollectionIntoDatabase(collections)
                    Resource.Success(collections)
                } ?: Resource.Error(response.message(), null)
            } else
                Resource.Error(response.message(), null)
        }

    override suspend fun saveCollectionIntoDatabase(collection: List<Collection>) =
        withContext(Dispatchers.IO) {
            dao.addCollection(collection)
        }

    override suspend fun deleteAllFromDatabase() = withContext(Dispatchers.IO) {
        dao.deleteAllCollections()
    }
}