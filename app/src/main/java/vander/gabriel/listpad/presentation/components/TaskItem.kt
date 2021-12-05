package vander.gabriel.listpad.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import vander.gabriel.listpad.domain.entities.Task
import vander.gabriel.listpad.presentation.theme.COLLECTION_ELEVATION

@ExperimentalMaterialApi
@Composable
fun TaskItem(
    task: Task,
    onCheckedChange: (Boolean) -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = COLLECTION_ELEVATION,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                modifier = Modifier.weight(1f),
                checked = task.checked,
                onCheckedChange = {
                    onCheckedChange(!task.checked)
                }
            )
            Text(
                modifier = Modifier.weight(8f),
                text = task.description,
                style = MaterialTheme.typography.h5.plus(
                    TextStyle(
                        textDecoration = if (task.checked) TextDecoration.LineThrough else null
                    )
                ),
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
@Preview
fun TaskItemCheckedPreview() {
    TaskItem(
        task = Task(
            id = "task1",
            checked = true,
            description = "Bathe In Milk"
        )
    )
}

@ExperimentalMaterialApi
@Composable
@Preview
fun TaskItemUncheckedPreview() {
    TaskItem(
        task = Task(
            id = "task2",
            checked = false,
            description = "Drive a Husky Sled"
        )
    )
}
