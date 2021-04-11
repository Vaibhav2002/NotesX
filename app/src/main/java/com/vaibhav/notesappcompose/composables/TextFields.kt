package com.vaibhav.notesappcompose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.theme.*

@Composable
fun OutlinedTextField(
    label: String,
    isPassword: Boolean = false,
    modifier: Modifier,
    textFieldColors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    var initialValue by remember { mutableStateOf("") }
    OutlinedTextField(
        value = initialValue,
        maxLines = 1,
        label = { Text(label) },
        onValueChange = { newText ->
            initialValue = newText
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier,
        colors = textFieldColors
    )
}


@Composable
fun SearchBar(
    label: String,
    onQueryChange: (String) -> Unit
) {
    var value by remember { mutableStateOf("") }
    val bgColor = if (isSystemInDarkTheme())
        lightGray
    else
        white

    TextField(
        value = value, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(RoundedCornerShape(20.dp)),
        label = {
            Text(text = label, color = black, modifier = Modifier.background(bgColor))
        },
        leadingIcon = {
            Icon(
                tint = black,
                painter = painterResource(id = R.drawable.search_icon),
                contentDescription = "Search"
            )
        },
        onValueChange = {
            value = it
            onQueryChange(value)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = bgColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}


//@Composable
//fun passwordTextField(
//    modifier: Modifier
//){
//    var initialValue by remember { mutableStateOf("") }
//    var passwordVisibility by remember{ mutableStateOf(Boolean)}
//    OutlinedTextField(
//        value = initialValue,
//        maxLines = 1,
//        label = { Text("Password") },
//        onValueChange = { newText ->
//            initialValue = newText
//        },
//        visualTransformation= PasswordVisualTransformation() ,
//        trailingIcon = { IconButton(
//            onClick = { /*TODO*/ }) {
//            Icons.Default.
//        }}
//        modifier = modifier,
//    )
//}