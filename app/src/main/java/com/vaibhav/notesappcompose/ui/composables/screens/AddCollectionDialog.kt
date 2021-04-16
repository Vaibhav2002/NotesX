package com.vaibhav.notesappcompose.ui.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.vaibhav.notesappcompose.ui.composables.OutlinedTextField
import com.vaibhav.notesappcompose.ui.theme.darkGray
import com.vaibhav.notesappcompose.ui.theme.lightGray
import com.vaibhav.notesappcompose.ui.viewmodels.CollectionViewModel

@Composable
fun AddCollectionDialog(
    isVisible: Boolean,
    viewModel: CollectionViewModel
) {

    val color = if (isSystemInDarkTheme()) lightGray else darkGray
    val isChecked = viewModel.isCollectionImportant.value
    val collectionName = viewModel.collectionName.value
    val loadingState = viewModel.addCollectionLoadingState.value
    if (isVisible) {
        Dialog(onDismissRequest = { viewModel.closeDialog() }) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface)
                    .clip(AbsoluteRoundedCornerShape(32.dp))
            ) {
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Add Collection",
                    style = MaterialTheme.typography.h5,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "Important Note",
                        style = MaterialTheme.typography.subtitle2,
                        modifier = Modifier.padding(end = 16.dp),
                        color = color
                    )
                    Switch(checked = isChecked, onCheckedChange = {
                        viewModel.onCollectionIsImportantChange(it)
                    })
                }

                Spacer(modifier = Modifier.padding(16.dp))
                OutlinedTextField(
                    value = collectionName,
                    label = "Collection Name",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onValueChange = {
                        viewModel.onCollectionNameChange(it)
                    }
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { viewModel.closeDialog() }) {
                        Text(text = "CANCEL")
                    }
                    TextButton(onClick = { viewModel.onSaveCollectionPressed() }) {
                        Text(text = "SAVE")
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
            }
        }
    }

}