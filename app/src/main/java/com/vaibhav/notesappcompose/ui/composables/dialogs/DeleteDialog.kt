package com.vaibhav.notesappcompose.ui.composables.dialogs

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.vaibhav.notesappcompose.ui.theme.darkGray
import com.vaibhav.notesappcompose.ui.theme.white


@Composable
fun DeleteDialog(
    text: String,
    isVisible: Boolean,
    onDeletePressed: () -> Unit,
    onDismissPressed: () -> Unit
) {

    val color = if (isSystemInDarkTheme()) darkGray else white

    if (isVisible) {
        AlertDialog(
            onDismissRequest = onDismissPressed,
            modifier = Modifier
                .clip(AbsoluteRoundedCornerShape(12.dp)),
            title = {
                Text(
                    text = "Confirm Delete",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onBackground
                )
            },
            buttons = {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                ) {
                    TextButton(onClick = onDismissPressed) {
                        Text(text = "CANCEL", style = MaterialTheme.typography.button)
                    }
                    TextButton(onClick = onDeletePressed) {
                        Text(text = "DELETE", style = MaterialTheme.typography.button)
                    }
                }
            },
            text = {
                Text(text = text, style = MaterialTheme.typography.body1)
            }
        )
    }

}