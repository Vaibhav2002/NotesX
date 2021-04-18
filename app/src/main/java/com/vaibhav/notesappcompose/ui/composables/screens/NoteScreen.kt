package com.vaibhav.notesappcompose.ui.composables.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.composables.Fab
import com.vaibhav.notesappcompose.ui.composables.NoteItem
import com.vaibhav.notesappcompose.ui.composables.SearchBar
import com.vaibhav.notesappcompose.ui.theme.darkGray
import com.vaibhav.notesappcompose.ui.theme.lightGray
import com.vaibhav.notesappcompose.ui.viewmodels.NotesViewModel


@ExperimentalFoundationApi
@Composable
fun NoteScreen(navController: NavController, collectionId: String, collectionName: String) {

    val viewModel: NotesViewModel =
        hiltNavGraphViewModel(backStackEntry = navController.currentBackStackEntry!!)

    remember {
        viewModel.setCollectionId(collectionId.toLong())
        viewModel.setCollectionName(collectionName)
        true
    }

    val loadingState = viewModel.loadingState.value
    val errorState = viewModel.errorState.value

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (screen, fab, loading) = createRefs()
        NoteMainScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .constrainAs(screen) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                },
            viewModel
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


        Fab(icon = R.drawable.ic_baseline_add_24, modifier = Modifier.constrainAs(fab) {
            bottom.linkTo(parent.bottom, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
        }) {
            navController.navigate("addNoteScreen/$collectionId")
        }
    }

}


@ExperimentalFoundationApi
@Composable
fun NoteMainScreen(
    modifier: Modifier,
    viewModel: NotesViewModel
) {
    val color = if (isSystemInDarkTheme()) lightGray else darkGray
    val notes = viewModel.notes.observeAsState(emptyList()).value
    val searchState = viewModel.searchQuery.observeAsState("")
    val importantNotesCount = viewModel.importantNotesCount.value
    val collectionName = viewModel.collectionName.value
    val count = notes.count {
        it.isImportant
    }
    viewModel.setImportantNotesCount(count.toLong())


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = collectionName,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "$importantNotesCount Important notes",
            style = MaterialTheme.typography.h6,
            color = color,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
        )

        SearchBar(label = "Search Notes...", value = searchState.value) {
            viewModel.onQueryTextChange(it)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(notes) {
                NoteItem(note = it) {

                }
            }
        }
    }
}