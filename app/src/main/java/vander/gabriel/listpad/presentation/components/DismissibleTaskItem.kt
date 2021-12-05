package vander.gabriel.listpad.presentation.components

import androidx.compose.material.DismissDirection
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import vander.gabriel.listpad.domain.entities.Task

@ExperimentalMaterialApi
@Composable
fun DismissibleTaskItem(
    task: Task,
    onDeleteTask: (Task) -> Unit,
    onTaskUpdate: (Task) -> Unit,
) {
    Dismissible(
        item = task,
        dismissed = onDeleteTask,
        directions = setOf(
            DismissDirection.EndToStart
        ),
        content = {
            TaskItem(
                task = task,
                onCheckedChange = { checked ->
                    val updatedTask = task.copy(
                        checked = checked
                    )
                    onTaskUpdate(updatedTask)
                }
            )
        }
    )
}