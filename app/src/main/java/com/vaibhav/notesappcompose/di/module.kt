package com.vaibhav.notesappcompose.di

import android.content.Context
import androidx.room.Room
import com.vaibhav.notesappcompose.data.local.CollectionDao
import com.vaibhav.notesappcompose.data.local.DataBase
import com.vaibhav.notesappcompose.data.local.NoteDao
import com.vaibhav.notesappcompose.data.models.mappers.CollectionMapper
import com.vaibhav.notesappcompose.data.models.mappers.NoteMapper
import com.vaibhav.notesappcompose.data.models.mappers.UserMapper
import com.vaibhav.notesappcompose.data.remote.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object module {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit =
        Retrofit.Builder().baseUrl("http://spring-boot-notes-app.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    @Provides
    fun providesUserMapper(): UserMapper = UserMapper()

    @Provides
    fun providesCollectionMapper(): CollectionMapper = CollectionMapper()

    @Provides
    fun providesNoteMapper(): NoteMapper = NoteMapper()

    @Singleton
    @Provides
    fun providesSharedPreferences(@ApplicationContext context: Context) =
        context.getSharedPreferences("NOTES", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesRoom(@ApplicationContext context: Context): DataBase = Room.databaseBuilder(
        context,
        DataBase::class.java,
        "My_Note_Datbase"
    )
        .fallbackToDestructiveMigration()
        .build()


    @Provides
    @Singleton
    fun providesCollectionDao(database: DataBase): CollectionDao = database.collectionDao

    @Provides
    @Singleton
    fun providesNoteDao(database: DataBase): NoteDao = database.noteDao

}