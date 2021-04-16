package com.vaibhav.notesappcompose.data.remote

import com.vaibhav.notesappcompose.data.models.remote.requests.CollectionBody
import com.vaibhav.notesappcompose.data.models.remote.requests.RegisterBody
import com.vaibhav.notesappcompose.data.models.remote.responses.AuthResponse
import com.vaibhav.notesappcompose.data.models.remote.responses.CollectionResponse
import com.vaibhav.notesappcompose.data.models.remote.responses.CollectionResponseItem
import retrofit2.Response
import retrofit2.http.*

interface Api {

    @GET("/user/login")
    suspend fun loginUser(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<AuthResponse>

    @POST("/user/register")
    suspend fun registerUser(
        @Body registerBody: RegisterBody
    ): Response<AuthResponse>

    @GET("/collection/{userId}")
    suspend fun getAllCollections(@Path("userId") userId: Long): Response<CollectionResponse>

    @POST("/collection")
    suspend fun addCollection(@Body collection: CollectionBody): Response<CollectionResponseItem>
}