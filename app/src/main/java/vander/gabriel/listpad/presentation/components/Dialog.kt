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
    onConfirm: () -> Unit = {},
    onDismiss: () -> Unit = {},
    content: @Composable (() -> Unit)? = null,
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
                    onClick = onConfirm,
                ) {
                    Text(confirmText)
                }
            },
            dismissButton = {
                Button(
                    onClick = onDismiss,
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
    )
}
