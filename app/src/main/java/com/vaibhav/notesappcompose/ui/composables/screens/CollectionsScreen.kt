package com.vaibhav.notesappcompose.ui.composables.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.composables.CollectionItem
import com.vaibhav.notesappcompose.ui.composables.Fab
import com.vaibhav.notesappcompose.ui.composables.SearchBar
import com.vaibhav.notesappcompose.ui.composables.UserAvatar
import com.vaibhav.notesappcompose.util.sampleCollections


@ExperimentalFoundationApi
@Composable
fun CollectionsScreen(navController: NavController) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (screen, fab) = createRefs()
        CollectionMainScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .constrainAs(screen) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }, navController
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
fun CollectionMainScreen(modifier: Modifier, navController: NavController) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Header(modifier = Modifier.padding(16.dp), navController)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(sampleCollections) {
                CollectionItem(collection = it) {
                    navController.navigate("noteScreen")
                }
            }
        }
    }
}

@Composable
fun Header(modifier: Modifier, navController: NavController) {
    Spacer(modifier = Modifier.padding(16.dp))
    UserAvatar(image = R.drawable.avatar, size = 150.dp, onClick = {
        navController.navigate("profileScreen")
    })
    Spacer(modifier = Modifier.padding(16.dp))
    Text(
        text = "My Collections",
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.onSurface,
        modifier = modifier
    )
    SearchBar(label = "Search Collections...") {

    }
    Spacer(modifier = Modifier.padding(8.dp))
}

