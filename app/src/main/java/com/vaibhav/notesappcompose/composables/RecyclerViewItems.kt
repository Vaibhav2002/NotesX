package com.vaibhav.notesappcompose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vaibhav.notesappcompose.data.models.Collection
import com.vaibhav.notesappcompose.ui.theme.blueDarkMode
import com.vaibhav.notesappcompose.ui.theme.blueLight
import com.vaibhav.notesappcompose.ui.theme.white

@Composable
fun CollectionItem(
    collection: Collection,
    onClick: () -> Unit
) {
    val isDark = isSystemInDarkTheme()
    val bgColor = if (collection.isImportant) {
        if (isDark)
            blueDarkMode
        else
            blueLight
    } else MaterialTheme.colors.surface
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(24.dp),
        backgroundColor = bgColor
    ) {
        Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.Center) {
            Text(text = collection.name, style = MaterialTheme.typography.h6)
        }
    }
    Spacer(modifier = Modifier.padding(8.dp))
}


