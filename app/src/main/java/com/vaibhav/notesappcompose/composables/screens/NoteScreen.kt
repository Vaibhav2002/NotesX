package com.vaibhav.notesappcompose.composables.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.composables.Fab
import com.vaibhav.notesappcompose.composables.NoteItem
import com.vaibhav.notesappcompose.composables.SearchBar
import com.vaibhav.notesappcompose.data.models.Note
import com.vaibhav.notesappcompose.ui.theme.darkGray
import com.vaibhav.notesappcompose.ui.theme.lightGray

@ExperimentalFoundationApi
@Composable
fun NoteScreen(navController: NavController) {

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (screen, fab) = createRefs()
        NoteMainScreen(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .constrainAs(screen) {
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

        }
    }

}


@ExperimentalFoundationApi
@Composable
fun NoteMainScreen(
    modifier: Modifier
) {
    val color = if (isSystemInDarkTheme()) lightGray else darkGray
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Tasks",
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .padding(top = 32.dp, start = 16.dp, end = 16.dp)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "10 Important notes",
            style = MaterialTheme.typography.h6,
            color = color,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
        )

        SearchBar(label = "Search Notes...") {

        }
        val sampleList = listOf<Note>(
            Note(
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor",
                true
            ),
            Note("Hello", true),
            Note(
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor",
                true
            ),
            Note("Hello", false),
            Note(
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor",
                false
            ),
            Note("Hello", false),
            Note(
                "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor",
                false
            ),
            Note("Hello", false)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            items(sampleList) {
                NoteItem(note = it) {

                }
            }
        }
    }
}

