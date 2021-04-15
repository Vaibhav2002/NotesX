package com.vaibhav.notesappcompose.data.repo.auth

import com.vaibhav.notesappcompose.data.api.Api
import com.vaibhav.notesappcompose.data.models.entity.User
import com.vaibhav.notesappcompose.data.models.mappers.UserMapper
import com.vaibhav.notesappcompose.data.models.remote.requests.RegisterBody
import com.vaibhav.notesappcompose.data.repo.preferences.PreferencesRepo
import com.vaibhav.notesappcompose.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepoImpl @Inject constructor(
    private val api: Api,
    private val userMapper: UserMapper,
    private val preferencesRepo: PreferencesRepo
) :
    AuthRepo {
    override suspend fun loginUser(email: String, password: String): Resource<User> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.loginUser(email, password)
                if (response.isSuccessful && response.body() != null) {
                    val user = userMapper.mapToDomainModel(response.body()!!)
                    preferencesRepo.saveUserData(user)
                    return@withContext Resource.Success(user)
                } else {
                    return@withContext Resource.Error(response.message())
                }
            } catch (e: Exception) {
                return@withContext Resource.Error(e.localizedMessage ?: "Oops something went wrong")
            }
        }

    override suspend fun registerUser(registerBody: RegisterBody): Resource<User> =
        withContext(Dispatchers.IO) {
            try {
                val response = api.registerUser(registerBody)
                if (response.isSuccessful && response.body() != null) {
                    val user = userMapper.mapToDomainModel(response.body()!!)
                    preferencesRepo.saveUserData(user)
                    return@withContext Resource.Success(user)
                } else {
                    return@withContext Resource.Error(response.message())
                }
            } catch (e: Exception) {
                return@withContext Resource.Error(e.localizedMessage ?: "Oops something went wrong")
            }
        }


    fun isUserLoggedIn() = preferencesRepo.isUserLoggedIn()
}