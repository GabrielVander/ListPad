package vander.gabriel.listpad.presentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.InternalCoroutinesApi
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.presentation.components.AddFloatingActionButton
import vander.gabriel.listpad.presentation.components.Loader
import vander.gabriel.listpad.presentation.components.Pill
import vander.gabriel.listpad.presentation.theme.CATEGORY_INDICATOR_SIZE
import vander.gabriel.listpad.presentation.theme.COLLECTION_ELEVATION
import vander.gabriel.listpad.presentation.theme.LARGE_PADDING
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

    val getSingleCollectionRequestState: RequestState<Collection> by collectionsViewModel
        .singleCollectionStateFlow
        .collectAsState()

    LaunchedEffect(key1 = collectionId) {
        collectionsViewModel.getCollection(collectionId)
    }

    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    text =
                    if (getSingleCollectionRequestState is RequestState.Success)
                        (getSingleCollectionRequestState as RequestState.Success<Collection>)
                            .data
                            .name
                    else "Loading")
            }
        },
        floatingActionButton = {
            AddFloatingActionButton(onClick = {

            })
        }
    ) {
        if (getSingleCollectionRequestState is RequestState.Loading) {
            Loader()
        } else if (getSingleCollectionRequestState is RequestState.Error) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                (getSingleCollectionRequestState as RequestState.Error)
                    .failure
                    .message?.let { message ->
                        Text(
                            text = message)
                    }
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun ListContent(collections: List<Collection>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        collections.forEach { collection ->
            CollectionItem(collection = collection)
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun CollectionItem(
    collection: Collection,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = COLLECTION_ELEVATION,
        onClick = {
            /* TODO */
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth(),
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = collection.name,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                if (collection.isUrgent) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        contentAlignment = Alignment.TopEnd
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(CATEGORY_INDICATOR_SIZE)
                        ) {
                            drawCircle(
                                color = Color.Red
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = collection.description,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))
            Pill(
                color = collection.category.color.copy(alpha = .5f),
                label = collection.category.name,
            )
        }
    }
}
