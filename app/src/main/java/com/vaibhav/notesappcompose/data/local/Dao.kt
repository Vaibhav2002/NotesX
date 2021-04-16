package com.vaibhav.notesappcompose.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vaibhav.notesappcompose.data.models.entity.Collection

@Dao
interface Dao {

//    @Query("SELECT * FROM collection_table WHERE name LIKE '%' +:query +'%' ORDER BY isImportant DESC")
//    fun getAllCollections(query: String): LiveData<List<Collection>>


    @Query("SELECT * FROM collection_table ORDER BY isImportant DESC")
    fun getAllCollections(): LiveData<List<Collection>>

    @Insert
    suspend fun addCollection(collections: List<Collection>)

    @Query("DELETE FROM collection_table")
    suspend fun deleteAllCollections()

}