package com.vaibhav.notesappcompose.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import com.vaibhav.notesappcompose.data.models.entity.Collection
import com.vaibhav.notesappcompose.data.models.entity.Note
import com.vaibhav.notesappcompose.ui.theme.getColorForCards

@Composable
fun CollectionItem(
    collection: Collection,
    onClick: () -> Unit
) {
    val bgColor = getColorForCards(isImportant = collection.isImportant)
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                onClick()
            },
        backgroundColor = bgColor,
        elevation = 0.dp
    ) {
        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
            Text(text = collection.name, style = MaterialTheme.typography.h6)
        }
    }
    Spacer(modifier = Modifier.padding(12.dp))
}


@Composable
fun NoteItem(
    note: Note,
    onClick: () -> Unit
){
    val bgColor = getColorForCards(isImportant = note.isImportant)
    Card(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() },
//        shape = RoundedCornerShape(12.dp),
        elevation = 0.dp,
        backgroundColor = bgColor
    ) {
        Text(text = note.text, style = MaterialTheme.typography.body1,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(bgColor)
                .clipToBounds()
        )
    }
}
