package vander.gabriel.listpad.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import vander.gabriel.listpad.domain.entities.Task

@Composable
fun NewTaskDialog(
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit,
    onSaveTask: (Task) -> Unit = {},
) {
    val (fieldValue, setFieldValue) = remember { mutableStateOf("") }

    Dialog(
        showDialog = showDialog,
        onConfirm = {
            onSaveTask(Task(description = fieldValue))
            setShowDialog(false)
        },
        onDismiss = {
            setShowDialog(false)
        },
        title = "Add task",
        content =
        {
            OutlinedTextField(
                value = fieldValue,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Description") },
                onValueChange = setFieldValue,
            )
        }
    )
}