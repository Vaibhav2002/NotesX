package com.vaibhav.notesappcompose.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun UserAvatar(image: Int, size: Dp, onClick: () -> Unit = {}) {
    Image(
        painter = painterResource(id = image),
        modifier = Modifier
            .height(size)
            .width(size)
            .clipToBounds()
            .clickable {
                onClick()
            },
        contentDescription = "Avatar"
    )
}