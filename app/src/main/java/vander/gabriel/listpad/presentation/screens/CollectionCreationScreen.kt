package vander.gabriel.listpad.presentation.screens

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import vander.gabriel.listpad.presentation.components.CollectionCreation

/**
 * The primary screen tasked with displaying all collections
 */
@Composable
fun CollectionCreationScreen() {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(text = "Collection creation")
            }
        },
    ) {
        CollectionCreation()
    }
}
