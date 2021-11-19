package vander.gabriel.listpad.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import vander.gabriel.listpad.presentation.view_models.CollectionsViewModel

/**
 * The primary screen tasked with displaying all collections
 */
@Composable
fun CollectionCreationScreen(collectionsViewModel: CollectionsViewModel = viewModel()) {
    val collectionsState = collectionsViewModel.state

    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Collection creation")
            }
        },
    ) {
        if (collectionsState.loading) {
            // TODO: Extract this as a separate widget
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
                OutlinedTextField(
                    value = "",
                    label = { Text("Name") },
                    onValueChange = {},
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = "",
                    label = { Text("Description") },
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 15.dp)
                )
            }
        }

    }
}
