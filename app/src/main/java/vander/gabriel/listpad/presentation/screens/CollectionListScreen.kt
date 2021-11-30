package vander.gabriel.listpad.presentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.InternalCoroutinesApi
import vander.gabriel.listpad.R
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.entities.NavigationRoutes
import vander.gabriel.listpad.presentation.components.Loader
import vander.gabriel.listpad.presentation.components.Pill
import vander.gabriel.listpad.presentation.theme.CATEGORY_INDICATOR_SIZE
import vander.gabriel.listpad.presentation.theme.COLLECTION_ELEVATION
import vander.gabriel.listpad.presentation.theme.LARGE_PADDING
import vander.gabriel.listpad.presentation.view_models.CollectionsViewModel

@InternalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun CollectionListScreen(
    collectionsViewModel: CollectionsViewModel = viewModel(),
    navigationController: NavHostController,
) {
    LaunchedEffect(key1 = Unit) {
        collectionsViewModel.updateCollectionList()
    }

    val loading by collectionsViewModel.loading
    val collections = collectionsViewModel.collectionsStateFlow.value

    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "All collections")
            }
        },
        floatingActionButton = {
            Button(
                onClick = {
                    navigationController
                        .navigate(
                            NavigationRoutes
                                .COLLECTION_CREATION
                                .route,
                        )
                }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create new collection",
                )
            }
        }
    ) {
        if (loading) {
            Loader()
        } else {
            if (collections.isEmpty()) {
                EmptyContent()
            } else {
                ListContent(collections)
            }
        }

    }
}

@ExperimentalMaterialApi
@Composable
fun ListContent(collections: List<Collection>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        collections.forEach {
                (
                    _,
                    name,
                    description,
                    isUrgent,
                    category,
                ),
            ->
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
                            text = name,
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                        if (isUrgent) {
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
                        text = description,
                        style = MaterialTheme.typography.subtitle1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Pill(color = category.color, label = category.name)
                }
            }
        }
    }
}

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            painter = painterResource(id = R.drawable.ic_dissatisfied_24),
            contentDescription = "Sad face icon",
        )
        Text(
            text = "No collections!",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
    }
}

@Composable
@Preview
private fun EmptyContentPreview() {
    EmptyContent()
}
