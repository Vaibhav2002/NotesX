package com.vaibhav.notesappcompose.ui.composables.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.composables.Fab
import com.vaibhav.notesappcompose.ui.composables.OutlinedTextField
import com.vaibhav.notesappcompose.ui.theme.darkGray
import com.vaibhav.notesappcompose.ui.theme.lightGray
import com.vaibhav.notesappcompose.ui.theme.white
import com.vaibhav.notesappcompose.ui.viewmodels.AddEditNoteViewModel
import timber.log.Timber

@Composable
fun AddEditNoteScreen(navController: NavController, collectionId: String, note: String) {

    val viewModel: AddEditNoteViewModel =
        hiltNavGraphViewModel(backStackEntry = navController.currentBackStackEntry!!)

    Timber.d(note)
    remember {
        if (note != "null") {
            viewModel.setNote(note)
        }
        viewModel.setCollectionId(collectionId.toLong())
        true
    }

    val errorState = viewModel.errorState.value
    val loadingState = viewModel.loadingState.value
    val navigateBackState = viewModel.navigateBackState.value
    val topText = viewModel.topText.value

    if (navigateBackState)
        navController.popBackStack()

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (screen, fab, loading) = createRefs()
        AddEditNoteMainScreen(
            topText,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .constrainAs(screen) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            viewModel = viewModel
        )

        if (errorState.isNotEmpty() && errorState.isNotBlank())
            Toast.makeText(LocalContext.current, errorState, Toast.LENGTH_SHORT).show()

        if (loadingState)
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(loading) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )
        Fab(icon = R.drawable.ic_baseline_check_24, modifier = Modifier.constrainAs(fab) {
            bottom.linkTo(parent.bottom, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
        }) {
            viewModel.onFabPressed()
        }
    }
}

@Composable
fun AddEditNoteMainScreen(
    topText: String,
    modifier: Modifier,
    viewModel: AddEditNoteViewModel
) {

    val isImportantState = viewModel.isImportantState.value
    val textState = viewModel.textState.value
    val color = if (isSystemInDarkTheme()) lightGray else darkGray
    val textFieldColor = if (isSystemInDarkTheme()) darkGray else white
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = topText,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Important Note",
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(end = 16.dp),
                color = color
            )
            Switch(checked = isImportantState, onCheckedChange = {
                viewModel.setIsImportantState(it)
            })
        }
        OutlinedTextField(
            value = textState,
            label = "Note",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            isSingleLine = false,
            textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = textFieldColor,
                textColor = MaterialTheme.colors.onBackground
            ),
            onValueChange = {
                viewModel.onTextChange(it)
            }
        )

    }
}