package vander.gabriel.listpad.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.InternalCoroutinesApi
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.entities.Task
import vander.gabriel.listpad.presentation.components.*
import vander.gabriel.listpad.presentation.theme.COLLECTION_ELEVATION
import vander.gabriel.listpad.presentation.utils.RequestState
import vander.gabriel.listpad.presentation.view_models.CollectionsViewModel

const val COLLECTION_ARGUMENT_KEY: String = "collectionId"

@InternalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun CollectionDetailsScreen(
    collectionsViewModel: CollectionsViewModel = viewModel(),
    collectionId: String,
) {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    val getSingleCollectionRequestState: RequestState<Collection> by collectionsViewModel
        .singleCollectionStateFlow
        .collectAsState()

    LaunchedEffect(key1 = collectionId) {
        collectionsViewModel.getCollection(collectionId)
    }

    Scaffold(
        topBar = {
            TopAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    val title =
                        if (getSingleCollectionRequestState is RequestState.Success) {
                            (getSingleCollectionRequestState as RequestState.Success<Collection>)
                                .data
                                .name
                        } else "Loading"

                    Text(text = title)
                }
            }
        },
        floatingActionButton = {
            AddFloatingActionButton(onClick = {
                setShowDialog(true)
            })
        }
    ) {
        when (getSingleCollectionRequestState) {
            is RequestState.Loading -> {
                Loader()
            }
            is RequestState.Error -> {
                (getSingleCollectionRequestState as RequestState.Error)
                    .failure
                    .message?.let { message ->
                        ErrorMessage(message)
                    }
            }
            is RequestState.Success -> {
                val collection =
                    (getSingleCollectionRequestState
                            as RequestState.Success<Collection>)
                        .data

                NewTaskDialog(
                    showDialog,
                    setShowDialog,
                    onSaveTask = { task ->
                        val updatedCollection = collection.copy(
                            tasks = collection.tasks.plus(task)
                        )

                        collectionsViewModel.updateCollection(updatedCollection)
                    }
                )

                Content(
                    collection = collection,
                    onTaskUpdate = {
                        val updatedCollection = collection.copy(
                            tasks = collection.tasks
                        )

                        collectionsViewModel.updateCollection(updatedCollection)
                        collectionsViewModel.getCollection(collectionId)
                    },
                    onDeleteTask = { taskToDelete ->
                        val updatedCollection = collection.copy(
                            tasks = collection
                                .tasks
                                .filter { originalTask -> originalTask != taskToDelete }
                        )

                        collectionsViewModel.updateCollection(updatedCollection)
                    }
                )
            }
            else -> {
                ErrorMessage("Oh no, something went wrong")
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun Content(
    collection: Collection,
    onTaskUpdate: () -> Unit = {},
    onDeleteTask: (Task) -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(collection.description)
        Spacer(modifier = Modifier.height(5.dp))
        if (collection.tasks.isEmpty()) {
            EmptyContent("No tasks!")
        } else {
            collection
                .tasks
                .forEach { task ->
                    DismissibleTask(task, onDeleteTask, onTaskUpdate)
                }

        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun DismissibleTask(
    task: Task,
    onDeleteTask: (Task) -> Unit,
    onTaskUpdate: () -> Unit,
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
                onCheckedChange = {
                    onTaskUpdate()
                }
            )
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun <T> Dismissible(
    item: T,
    directions: Set<DismissDirection> = setOf(
        DismissDirection.StartToEnd,
        DismissDirection.EndToStart,
    ),
    defaultBackgroundColor: Color = Color.White,
    dismissedToStartBackgroundColor: Color = Color.Red,
    dismissedToEndBackgroundColor: Color = Color.Green,
    startToEndIcon: ImageVector = Icons.Default.Done,
    endToStartIcon: ImageVector = Icons.Default.Delete,
    dismissed: (item: T) -> Unit,
    content: @Composable () -> Unit,
) {
    val dismissState = rememberDismissState()
    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
        dismissed(item)
    }

    SwipeToDismiss(
        state = dismissState,
        modifier = Modifier.padding(vertical = 1.dp),
        directions = directions,
        dismissThresholds = { direction ->
            FractionalThreshold(
                if (direction == DismissDirection.StartToEnd) 0.25f
                else 0.5f
            )
        },
        background = {
            val direction = dismissState.dismissDirection ?: return@SwipeToDismiss

            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> defaultBackgroundColor
                    DismissValue.DismissedToStart -> dismissedToStartBackgroundColor
                    DismissValue.DismissedToEnd -> dismissedToEndBackgroundColor
                }
            )
            val alignment = when (direction) {
                DismissDirection.StartToEnd -> Alignment.CenterStart
                DismissDirection.EndToStart -> Alignment.CenterEnd
            }
            val icon = when (direction) {
                DismissDirection.StartToEnd -> startToEndIcon
                DismissDirection.EndToStart -> endToStartIcon
            }
            val scale by animateFloatAsState(
                if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
            )

            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 20.dp),
                contentAlignment = alignment
            ) {
                Icon(
                    icon,
                    contentDescription = "Localized description",
                    modifier = Modifier.scale(scale)
                )
            }
        },
        dismissContent = {
            content()
        }
    )
}

@ExperimentalMaterialApi
@Composable
private fun TaskItem(
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
                    task.checked = !task.checked
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
    TaskItem(task = Task(
        checked = true,
        description = "Bathe In Milk"
    ))
}

@ExperimentalMaterialApi
@Composable
@Preview
fun TaskItemUncheckedPreview() {
    TaskItem(task = Task(
        checked = false,
        description = "Drive a Husky Sled"
    ))
}
