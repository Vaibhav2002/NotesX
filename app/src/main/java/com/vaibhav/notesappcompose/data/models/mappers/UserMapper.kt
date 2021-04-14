package com.vaibhav.notesappcompose.data.models.mappers

import com.vaibhav.notesappcompose.data.models.entity.User
import com.vaibhav.notesappcompose.data.models.remote.responses.AuthResponse
import com.vaibhav.notesappcompose.data.util.mapper.DomainMappers

class UserMapper : DomainMappers<AuthResponse, User> {
    override fun mapToDomainModel(model: AuthResponse): User {
        return User(
            id = model.id,
            email = model.email,
            username = model.name
        )
    }

    override fun mapToDomainModelList(modelList: List<AuthResponse>): List<User> {
        return modelList.map {
            mapToDomainModel(it)
        }
    }
}