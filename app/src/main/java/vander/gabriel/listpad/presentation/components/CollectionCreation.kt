package vander.gabriel.listpad.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import arrow.core.getOrElse
import arrow.core.getOrHandle
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.entities.CollectionCategory
import vander.gabriel.listpad.domain.value_objects.CollectionNameValueObject
import vander.gabriel.listpad.domain.value_objects.ObjectFailure
import vander.gabriel.listpad.presentation.theme.CATEGORY_DROP_DOWN_HEIGHT
import vander.gabriel.listpad.presentation.view_models.CollectionCreationState
import vander.gabriel.listpad.presentation.view_models.CollectionCreationViewModel
import java.util.*

@Composable
fun CollectionCreation(
    collectionCreationViewModel: CollectionCreationViewModel = viewModel(),
    onSave: (collection: Collection) -> Unit = {},
) {
    val collectionsState: CollectionCreationState = collectionCreationViewModel.state

    val (collectionNameValueObject, setCollectionNameValueObject) = remember {
        mutableStateOf(CollectionNameValueObject())
    }
    val collectionNameValue: String = collectionNameValueObject.value.getOrElse { "" }
    val isError = collectionNameValueObject.value.isLeft()

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = collectionNameValue,
            label = { Text("Name") },
            isError = isError,
            onValueChange = { setCollectionNameValueObject(collectionNameValueObject.validate(it)) },
            modifier = Modifier.fillMaxWidth()
        )
        collectionNameValueObject.value.getOrHandle { objectFailure ->
            val errorMessage: String = getErrorMessage(objectFailure)

            Text(
                errorMessage,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.error,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
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
        val allFieldsAreValid = collectionNameValueObject
            .value
            .isRight()

        Button(
            enabled = allFieldsAreValid,
            onClick = {
                val collection = Collection(
                    id = UUID.randomUUID().toString(),
                    name = collectionNameValue,
                    description = "Some description",
                    isUrgent = true,
                    category = CollectionCategory.SHOPPING
                )
                onSave(collection)
            },
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
private fun getErrorMessage(objectFailure: ObjectFailure<String>) =
    when (objectFailure) {
        is CollectionNameValueObject.Companion.EmptyObjectFailure -> {
            "Name must be set"
        }
        is CollectionNameValueObject.Companion.NotUniqueObjectFailure -> {
            "Name must be unique"
        }
        else -> {
            "Unknown error"
        }
    }
