package com.vaibhav.notesappcompose.data.repo.collection

import androidx.lifecycle.LiveData
import com.vaibhav.notesappcompose.data.local.CollectionDao
import com.vaibhav.notesappcompose.data.models.entity.Collection
import com.vaibhav.notesappcompose.data.models.mappers.CollectionMapper
import com.vaibhav.notesappcompose.data.models.remote.requests.CollectionBody
import com.vaibhav.notesappcompose.data.remote.Api
import com.vaibhav.notesappcompose.data.util.mapToErrorResponse
import com.vaibhav.notesappcompose.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class CollectionRepoImpl @Inject constructor(
    private val collectionDao: CollectionDao,
    private val api: Api,
    private val collectionMapper: CollectionMapper
) : CollectionRepo {

    override fun getAllCollections(query: String): LiveData<List<Collection>> =
        collectionDao.getAllCollectionsBasedOnQuery("$query%")

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
                val collectionEntity = collectionMapper.mapToDomainModel(it)
                saveCollectionIntoDatabase(listOf(collectionEntity))
                return@withContext Resource.Success(collectionEntity)
            } ?: Resource.Error(response.message(), null)
        } else {
            val error = mapToErrorResponse(response.errorBody())
            return@withContext Resource.Error(
                error.message
            )
        }
    }


    override suspend fun fetchCollections(userId: Long): Resource<List<Collection>> =
        withContext(Dispatchers.IO) {
            val response = api.getAllCollections(userId)
            if (response.isSuccessful) {
                response.body()?.let {
                    deleteAllFromDatabase()
                    val collections = collectionMapper.mapToDomainModelList(it)
                    saveCollectionIntoDatabase(collections)
                    return@withContext Resource.Success(collections)
                } ?: Resource.Error(response.message(), null)
            } else {
                val error = mapToErrorResponse(response.errorBody())
                return@withContext Resource.Error(
                    error.message
                )
            }
        }

    override suspend fun saveCollectionIntoDatabase(collection: List<Collection>) =
        withContext(Dispatchers.IO) {
            collectionDao.addCollection(collection)
        }

    override suspend fun deleteAllFromDatabase() = withContext(Dispatchers.IO) {
        collectionDao.deleteAllCollections()
    }

    override suspend fun deleteCollection(collection: Collection): Resource<Collection> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.deleteCollection(collection.id)
                if (response.isSuccessful) {
                    response.body()?.let {
                        val collection = collectionMapper.mapToDomainModel(it)
                        deleteCollectionFromDb(collection)
                        return@withContext Resource.Success(collection)
                    } ?: return@withContext Resource.Error(response.message())
                } else {
                    val error = mapToErrorResponse(response.errorBody())
                    return@withContext Resource.Error(
                        error.message
                    )
                }
            } catch (e: Exception) {
                return@withContext Resource.Error(e.localizedMessage ?: "Oops something went wrong")
            }
        }

    override suspend fun deleteCollectionFromDb(collection: Collection) =
        withContext(Dispatchers.IO) {
            collectionDao.deleteCollection(collection.id)
        }
}