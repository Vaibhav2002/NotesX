package com.vaibhav.notesappcompose.ui.composables.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.composables.Fab
import com.vaibhav.notesappcompose.ui.composables.OutlinedTextField
import com.vaibhav.notesappcompose.ui.theme.darkGray
import com.vaibhav.notesappcompose.ui.theme.lightGray
import com.vaibhav.notesappcompose.ui.theme.white

@Composable
fun AddNoteScreen(navController: NavController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (screen, fab) = createRefs()
        AddNoteMainScreen(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .constrainAs(screen) {
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
            navController.popBackStack()
        }
    }
}

@Composable
fun AddNoteMainScreen(
    modifier: Modifier
) {

    var isChecked by remember { mutableStateOf(false) }
    val color = if (isSystemInDarkTheme()) lightGray else darkGray
    val textFieldColor = if (isSystemInDarkTheme()) darkGray else white
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create\nNote",
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
            Switch(checked = isChecked, onCheckedChange = {
                isChecked = it
            })
        }
        OutlinedTextField(
            value = "",
            label = "Note",
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = textFieldColor
            ),
            onValueChange = {}
        )

    }
}