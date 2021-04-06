package com.vaibhav.notesappcompose.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vaibhav.notesappcompose.R

@Composable
fun UserAvatar(image: Int, size:Dp) {
    Image(
        painter = painterResource(id = image),
        modifier = Modifier
            .height(size)
            .width(size),
        contentDescription = "Avatar"
    )
}