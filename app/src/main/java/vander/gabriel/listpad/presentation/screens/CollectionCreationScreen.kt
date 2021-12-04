package vander.gabriel.listpad.presentation.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.InternalCoroutinesApi
import vander.gabriel.listpad.presentation.components.CollectionCreation
import vander.gabriel.listpad.presentation.view_models.CollectionsViewModel

@InternalCoroutinesApi
@Composable
fun CollectionCreationScreen(
    collectionsViewModel: CollectionsViewModel = viewModel(),
    navigationController: NavHostController,
) {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Collection creation")
            }
        },
    ) {
        CollectionCreation(onSave = {
            collectionsViewModel.updateCollectionList()
            navigationController.popBackStack()
        })
    }
}
