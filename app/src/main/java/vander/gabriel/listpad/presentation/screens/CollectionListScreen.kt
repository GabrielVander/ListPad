package vander.gabriel.listpad.presentation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
                Text(text = "Collections")
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
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                collectionsState.dataToDisplayOnScreen.forEach { (_, name, description) ->
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        elevation = 2.dp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .clickable {}
                    ) {
                        Row(modifier = Modifier.animateContentSize()) {
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
                                                color = Color.LightGray,
                                            )
                                        ),
                                    overflow = TextOverflow.Ellipsis,
                                    maxLines = 4
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun ProfileContentComposable(name: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(start = 8.dp),
        verticalArrangement = Arrangement.aligned(Alignment.CenterVertically)
    ) {
        Text(name, fontWeight = FontWeight.Bold)
        Text(
            description,
            style = MaterialTheme.typography.body2
        )
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
