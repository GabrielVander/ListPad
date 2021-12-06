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
import vander.gabriel.listpad.presentation.theme.CATEGORY_DROP_DOWN_HEIGHT
import vander.gabriel.listpad.presentation.view_models.CollectionCreationState
import vander.gabriel.listpad.presentation.view_models.CollectionCreationViewModel

@Composable
fun CollectionCreation(
    collectionCreationViewModel: CollectionCreationViewModel = viewModel(),
    onSave: () -> Unit = {},
) {
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
            onValueChange = { newName -> collectionCreationViewModel.onNameChange(newName) },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = collectionsState.description,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Description") },
            onValueChange = { newDescription ->
                collectionCreationViewModel.onDescriptionChange(newDescription)
            },
        )
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            CategoryDropdown(
                modifier = Modifier.weight(5f),
                selectedCategory = collectionsState.category,
                onCategorySelected = { category ->
                    collectionCreationViewModel.onCategorySelected(category)
                },
                items = CollectionCategory.values().toList(),
            )
            Spacer(modifier = Modifier.width(15.dp))
            Row(
                modifier = Modifier
                    .weight(3f)
                    .height(CATEGORY_DROP_DOWN_HEIGHT),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = collectionsState.isUrgent,
                    onCheckedChange = { newValue ->
                        collectionCreationViewModel.onIsUrgentChange(newValue)
                    }
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Urgent")
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        Button(onClick = {
            collectionCreationViewModel.onSave()
            onSave()
        }) {
            Text(text = "Save")
        }
    }
}
