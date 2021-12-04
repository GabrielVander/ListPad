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
import androidx.navigation.NavHostController
import kotlinx.coroutines.InternalCoroutinesApi
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.entities.NavigationRoutes
import vander.gabriel.listpad.presentation.components.AddFloatingActionButton
import vander.gabriel.listpad.presentation.components.EmptyContent
import vander.gabriel.listpad.presentation.components.Loader
import vander.gabriel.listpad.presentation.components.Pill
import vander.gabriel.listpad.presentation.theme.CATEGORY_INDICATOR_SIZE
import vander.gabriel.listpad.presentation.theme.COLLECTION_ELEVATION
import vander.gabriel.listpad.presentation.theme.LARGE_PADDING
import vander.gabriel.listpad.presentation.utils.RequestState
import vander.gabriel.listpad.presentation.view_models.CollectionsViewModel

@InternalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun CollectionListScreen(
    collectionsViewModel: CollectionsViewModel = viewModel(),
    navigationController: NavHostController,
) {
    val getCollectionsRequestState: RequestState<List<Collection>> by collectionsViewModel
        .collectionsStateFlow
        .collectAsState()

    LaunchedEffect(key1 = Unit) {
        collectionsViewModel.updateCollectionList()
    }

    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "All collections")
            }
        },
        floatingActionButton = {
            AddFloatingActionButton(onClick = {
                navigationController
                    .navigate(
                        NavigationRoutes
                            .COLLECTION_CREATION
                            .route,
                    )
            })
        }
    ) {
        when (getCollectionsRequestState) {
            is RequestState.Loading -> {
                Loader()
            }
            is RequestState.Success -> {
                if ((getCollectionsRequestState as RequestState.Success<List<Collection>>)
                        .data
                        .isEmpty()
                ) {
                    EmptyContent()
                } else {
                    ListContent(
                        (getCollectionsRequestState as RequestState.Success<List<Collection>>)
                            .data,
                        onCollectionClick = { (id) ->
                            navigationController
                                .navigate(
                                    route = "collectionDetails/${id}"
                                )
                        }
                    )
                }
            }
            else -> {
                EmptyContent()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun ListContent(
    collections: List<Collection>,
    onCollectionClick: (collection: Collection) -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        collections.forEach { collection ->
            CollectionItem(collection = collection, onClick = onCollectionClick)
        }
    }
}

@ExperimentalMaterialApi
@Composable
private fun CollectionItem(
    collection: Collection,
    onClick: (collection: Collection) -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RectangleShape,
        elevation = COLLECTION_ELEVATION,
        onClick = { onClick(collection) }
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
