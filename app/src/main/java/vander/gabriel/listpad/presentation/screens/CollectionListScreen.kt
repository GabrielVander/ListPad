package vander.gabriel.listpad.presentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import vander.gabriel.listpad.domain.entities.NavigationRoutes
import vander.gabriel.listpad.presentation.components.Loader
import vander.gabriel.listpad.presentation.components.Pill
import vander.gabriel.listpad.presentation.theme.CATEGORY_INDICATOR_SIZE
import vander.gabriel.listpad.presentation.theme.COLLECTION_ELEVATION
import vander.gabriel.listpad.presentation.theme.LARGE_PADDING
import vander.gabriel.listpad.presentation.view_models.CollectionsViewModel

@ExperimentalMaterialApi
@Composable
fun CollectionListScreen(
    collectionsViewModel: CollectionsViewModel = viewModel(),
    navigationController: NavHostController,
) {
    val collectionsState = collectionsViewModel.state

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
        if (collectionsState.loading) {
            Loader()
        } else {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                collectionsState.dataToDisplayOnScreen.forEach {
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

    }
}
