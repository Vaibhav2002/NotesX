package com.vaibhav.notesappcompose.ui.composables.screens

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltNavGraphViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.composables.CollectionItem
import com.vaibhav.notesappcompose.ui.composables.Fab
import com.vaibhav.notesappcompose.ui.composables.SearchBar
import com.vaibhav.notesappcompose.ui.composables.UserAvatar
import com.vaibhav.notesappcompose.ui.composables.dialogs.DeleteDialog
import com.vaibhav.notesappcompose.ui.theme.darkGray
import com.vaibhav.notesappcompose.ui.theme.lightGray
import com.vaibhav.notesappcompose.ui.viewmodels.CollectionViewModel


@ExperimentalFoundationApi
@Composable
fun CollectionsScreen(navController: NavController) {

    val viewModel: CollectionViewModel =
        hiltNavGraphViewModel(backStackEntry = navController.currentBackStackEntry!!)

    val loadingState = viewModel.loadingState.value
    val errorState = viewModel.errorState.value
    val dialogState = viewModel.dialogState.value
    val deleteDialogState = viewModel.isDeleteDialogVisible.value


    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val (screen, fab, progressBar, dialog) = createRefs()
        CollectionMainScreen(
            viewModel = viewModel,
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


        if (errorState.isNotEmpty())
            Toast.makeText(LocalContext.current, errorState, Toast.LENGTH_SHORT).show()

        AddCollectionDialog(
            isVisible = dialogState,
            viewModel = viewModel
        )
        DeleteDialog(
            text = viewModel.deleteText,
            isVisible = deleteDialogState,
            onDismissPressed = {
                viewModel.dismissDeleteCollectionDialog()
            },
            onDeletePressed = {
                viewModel.onDeleteDialogPressed()
            }
        )

        if (loadingState)
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(progressBar) {
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
            viewModel.setDialogState(true)
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun CollectionMainScreen(
    viewModel: CollectionViewModel,
    modifier: Modifier,
    navController: NavController
) {

    val collections = viewModel.collections.observeAsState().value ?: emptyList()
    val imp = collections.count {
        it.isImportant
    }
    viewModel.setImportantCollectionCount(imp.toLong())
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Header(modifier = Modifier.padding(16.dp), navController, viewModel)
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(collections) {
                CollectionItem(collection = it,
                    onClick = { collection ->
                        navController.navigate("noteScreen/${collection.id}/${collection.name}")
                    },
                    onItemLongPress = { collection ->
                        viewModel.showDeleteCollectionDialog(collection)
                    })
            }
        }
    }
}

@Composable
fun Header(modifier: Modifier, navController: NavController, viewModel: CollectionViewModel) {

    val searchValue = viewModel.queryState.observeAsState(initial = "")
    val importantCollectionState = viewModel.importantCollectionState.value
    val color = if (isSystemInDarkTheme()) lightGray else darkGray

    Spacer(modifier = Modifier.padding(16.dp))
    UserAvatar(image = R.drawable.avatar, size = 150.dp, onClick = {
        navController.navigate("profileScreen")
    })
    Text(
        text = "My Collections",
        style = MaterialTheme.typography.h4,
        color = MaterialTheme.colors.onSurface,
        modifier = modifier
    )
    Text(
        text = "$importantCollectionState Important collections",
        style = MaterialTheme.typography.h6,
        color = color,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 32.dp)
    )
    SearchBar(label = "Search Collections...", value = searchValue.value) {
        viewModel.onQueryTextChange(it)
    }
    Spacer(modifier = Modifier.padding(16.dp))
}

