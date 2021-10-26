package vander.gabriel.listpad.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import vander.gabriel.listpad.presentation.view_models.CollectionsViewModel

/**
 * The primary screen tasked with displaying all collections
 */
@Composable
fun CollectionListScreen(collectionsViewModel: CollectionsViewModel = viewModel()) {
    val collectionsState = collectionsViewModel.state

    // A surface container using the 'background' color from the theme
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "All collections")
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
                collectionsState.dataToDisplayOnScreen.forEach { (_, name, description, isUrgent) ->
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        backgroundColor = Color.LightGray,
                        elevation = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {}
                    ) {
                        Row {
                            Column(
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .fillMaxWidth(0.8f)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = name,
                                    style = MaterialTheme.typography.h6
                                )
                                Text(
                                    text = description,
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
                            }
                            if (isUrgent) {
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

/**
 * The default preview of the entire screen
 */
@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    CollectionListScreen()
}
