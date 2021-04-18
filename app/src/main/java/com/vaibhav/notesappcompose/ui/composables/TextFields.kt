package com.vaibhav.notesappcompose.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.vaibhav.notesappcompose.R
import com.vaibhav.notesappcompose.ui.theme.black
import com.vaibhav.notesappcompose.ui.theme.lightGray
import com.vaibhav.notesappcompose.ui.theme.white

@Composable
fun OutlinedTextField(
    value: String,
    label: String,
    isSingleLine: Boolean = true,
    isPassword: Boolean = false,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    textFieldColors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        textColor = MaterialTheme.colors.onBackground
    )
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = isSingleLine,
        visualTransformation =
        if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = modifier,
        colors = textFieldColors
    )
}


@Composable
fun SearchBar(
    value: String,
    label: String,
    onQueryChange: (String) -> Unit
) {
    val bgColor = if (isSystemInDarkTheme())
        lightGray
    else
        white

    TextField(
        value = value, modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clip(AbsoluteRoundedCornerShape(20.dp)),
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
        onValueChange = onQueryChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = bgColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun SwitchTOLoginOrRegisterTexts(
    modifier: Modifier,
    text1: String,
    text2: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text1,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.padding(2.dp))
        Text(
            text = text2,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.clickable { onClick() }
        )
    }
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