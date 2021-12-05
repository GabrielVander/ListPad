package vander.gabriel.listpad.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.InternalCoroutinesApi
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.entities.Task
import vander.gabriel.listpad.presentation.components.*
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
            NewItemButton(onClick = {
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
                    onTaskUpdate = { updatedTask ->
                        val updatedCollection = collection.copy(
                            tasks = collection
                                .tasks
                                .map { task ->
                                    if (task.id == updatedTask.id) updatedTask
                                    else task
                                }
                        )

                        collectionsViewModel.updateCollection(updatedCollection)
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
    onTaskUpdate: (Task) -> Unit = {},
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
