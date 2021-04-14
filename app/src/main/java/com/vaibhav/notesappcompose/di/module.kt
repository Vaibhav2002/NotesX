package com.vaibhav.notesappcompose.di

import com.vaibhav.notesappcompose.data.api.Api
import com.vaibhav.notesappcompose.data.models.mappers.CollectionMapper
import com.vaibhav.notesappcompose.data.models.mappers.UserMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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


}