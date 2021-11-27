package vander.gabriel.listpad.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import vander.gabriel.listpad.domain.entities.CollectionCategory
import vander.gabriel.listpad.presentation.view_models.CollectionCreationState
import vander.gabriel.listpad.presentation.view_models.CollectionCreationViewModel

/**
 * The collection creation composable
 */
@Composable
fun CollectionCreation(collectionCreationViewModel: CollectionCreationViewModel = viewModel()) {
    val collectionsState: CollectionCreationState = collectionCreationViewModel.state

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = collectionsState.name,
            label = { Text("Name") },
            onValueChange = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = collectionsState.description,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Description") },
            onValueChange = { /*TODO*/ },
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            CategoryDropdown(
                selectedCategory = collectionsState.category,
                onCategorySelected = { category ->
                    collectionCreationViewModel.onCategorySelected(category)
                },
                items = CollectionCategory.values().toList(),
            )
            Row {
                Checkbox(
                    checked = collectionsState.isUrgent,
                    onCheckedChange = { /*TODO*/ }
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Urgent")
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Save")
        }
    }
}
