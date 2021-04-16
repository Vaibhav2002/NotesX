package com.vaibhav.notesappcompose.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.clip(AbsoluteRoundedCornerShape(12.dp)),
        onClick = onClick,
    ) {
        Text(
            text,
            style = MaterialTheme.typography.button,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun SecondaryButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick() },
        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
        modifier = modifier,
        shape = AbsoluteRoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colors.primary,
            backgroundColor = MaterialTheme.colors.background
        ),
    ) {
        Text(
            text,
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
fun Fab(
    icon: Int,
    modifier: Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(modifier = modifier, onClick = onClick) {
        Icon(painter = painterResource(id = icon), contentDescription = "FAB icon")
    }
}




