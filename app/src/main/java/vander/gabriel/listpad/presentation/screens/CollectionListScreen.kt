package vander.gabriel.listpad.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.InternalCoroutinesApi
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.entities.NavigationRoutes
import vander.gabriel.listpad.presentation.components.*
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
            NewItemButton(onClick = {
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
                    EmptyContent("No collections!")
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
                ErrorMessage("Oh no, something went wrong")
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
