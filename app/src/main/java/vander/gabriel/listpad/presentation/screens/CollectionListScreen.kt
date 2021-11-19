package vander.gabriel.listpad.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import vander.gabriel.listpad.domain.entities.NavigationRoutes
import vander.gabriel.listpad.presentation.view_models.CollectionsViewModel
import vander.gabriel.listpad.presentation.widgets.Pill

/**
 * The primary screen tasked with displaying all collections
 */
@Composable
fun CollectionListScreen(
    collectionsViewModel: CollectionsViewModel = viewModel(),
    navigationController: NavHostController,
) {
    val collectionsState = collectionsViewModel.state

    // A surface container using the 'background' color from the theme
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
            Column(
                Modifier.fillMaxSize(),
                Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                collectionsState.dataToDisplayOnScreen.forEach {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        backgroundColor = Color.LightGray,
                        elevation = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {}
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                        ) {
                            Row {
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .fillMaxWidth(0.8f),
                                    verticalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Text(
                                        text = it.name,
                                        style = MaterialTheme.typography.h6
                                    )
                                    Text(
                                        text = it.description,
                                        textAlign = TextAlign.Start,
                                        style = MaterialTheme
                                            .typography
                                            .subtitle2
                                            .plus(
                                                TextStyle(
                                                    color = Color.Gray,
                                                )
                                            ),
                                        overflow = TextOverflow.Ellipsis,
                                        maxLines = 2
                                    )
                                    Pill(color = it.category.color, label = it.category.name)
                                }
                                if (it.isUrgent) {
                                    Icon(
                                        imageVector = Icons.Outlined.Warning,
                                        contentDescription =
                                        "Warning icon signaling this collection is urgent",
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .align(Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}
