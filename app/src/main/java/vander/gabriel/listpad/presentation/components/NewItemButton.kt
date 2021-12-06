package vander.gabriel.listpad.presentation.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NewItemButton(
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = {
            onClick()
        },
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add icon",
            tint = Color.White
        )
    }
}

@Composable
@Preview
private fun AddFloatingButtonPreview() {
    NewItemButton {

    }
}