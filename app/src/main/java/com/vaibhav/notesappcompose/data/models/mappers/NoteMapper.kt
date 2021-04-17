package com.vaibhav.notesappcompose.data.models.mappers

import com.vaibhav.notesappcompose.data.models.entity.Note
import com.vaibhav.notesappcompose.data.models.remote.responses.NoteResponseItem
import com.vaibhav.notesappcompose.data.util.mapper.DomainMappers

class NoteMapper : DomainMappers<NoteResponseItem, Note> {

    override fun mapToDomainModel(model: NoteResponseItem): Note {
        return Note(
            id = model.id,
            text = model.text,
            isImportant = model.isImportant,
            timeStamp = model.timeStamp
        )
    }

    override fun mapToDomainModelList(modelList: List<NoteResponseItem>): List<Note> {
        return modelList.map {
            mapToDomainModel(it)
        }
    }
}