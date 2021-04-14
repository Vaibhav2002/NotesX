package com.vaibhav.notesappcompose.data.models.mappers

import com.vaibhav.notesappcompose.data.models.entity.Collection
import com.vaibhav.notesappcompose.data.models.remote.responses.CollectionResponseItem
import com.vaibhav.notesappcompose.data.util.mapper.DomainMappers

class CollectionMapper : DomainMappers<CollectionResponseItem, Collection> {
    override fun mapToDomainModel(model: CollectionResponseItem): Collection {
        return Collection(
            id = model.id,
            name = model.name,
            isImportant = model.isImportant,
            timeStamp = model.timeStamp
        )
    }


    override fun mapToDomainModelList(modelList: List<CollectionResponseItem>): List<Collection> {
        return modelList.map {
            mapToDomainModel(it)
        }
    }


}