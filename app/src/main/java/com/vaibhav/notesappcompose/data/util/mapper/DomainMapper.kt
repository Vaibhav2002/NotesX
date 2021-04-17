package com.vaibhav.notesappcompose.data.util.mapper

interface DomainMappers<NetworkModel, DomainModel> {

    fun mapToDomainModel(model: NetworkModel): DomainModel
    fun mapToDomainModelList(modelList: List<NetworkModel>): List<DomainModel>
}