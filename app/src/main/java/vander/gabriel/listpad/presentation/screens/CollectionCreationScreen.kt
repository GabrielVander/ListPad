package vander.gabriel.listpad.presentation.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import vander.gabriel.listpad.presentation.components.CollectionCreation
import vander.gabriel.listpad.presentation.components.Loader
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
            Loader()
        } else {
            CollectionCreation()
        }

    }
}
