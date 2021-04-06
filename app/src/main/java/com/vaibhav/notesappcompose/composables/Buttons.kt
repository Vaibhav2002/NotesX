package com.vaibhav.notesappcompose.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vaibhav.notesappcompose.R


@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            text,
            style = MaterialTheme.typography.button,
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



@Preview
@Composable
fun ButtonPreview() {
    PrimaryButton(text = "LOGIN", modifier = Modifier
        .fillMaxWidth(), onClick = {})
}

