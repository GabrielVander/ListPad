package vander.gabriel.listpad.presentation.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Dialog(
    showDialog: Boolean,
    title: String,
    confirmText: String = "Confirm",
    dismissText: String = "Dismiss",
    content: @Composable (() -> Unit)? = null,
    setShowDialog: (Boolean) -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text(title)
            },
            confirmButton = {
                Button(
                    onClick = {
                        setShowDialog(false)
                    },
                ) {
                    Text(confirmText)
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        setShowDialog(false)
                    },
                ) {
                    Text(dismissText)
                }
            },
            text = content,
        )
    }
}

@Composable
@Preview
fun DialogPreview() {
    Dialog(
        showDialog = true,
        title = "Dialog",
        content = { Text(text = "Some content") },
        setShowDialog = {}
    )
}
