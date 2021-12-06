package vander.gabriel.listpad.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.entities.CollectionCategory
import vander.gabriel.listpad.domain.usecases.SaveCollectionUseCase
import vander.gabriel.listpad.domain.value_objects.CollectionNameValueObject
import java.util.*

data class CollectionCreationState(
    val name: String,
    val description: String,
    val category: CollectionCategory,
    val isUrgent: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)

class CollectionCreationViewModel(
    private val saveCollectionUseCase: SaveCollectionUseCase = SaveCollectionUseCase(),
) :
    ViewModel() {

    var state: CollectionCreationState by mutableStateOf(
        CollectionCreationState(
            name = "",
            description = "",
            category = CollectionCategory.GENERAL,
            isUrgent = false,
        )
    )

    private val _collectionNameValueObject: MutableStateFlow<CollectionNameValueObject> =
        MutableStateFlow(CollectionNameValueObject())
    val collectionNameValueObject: StateFlow<CollectionNameValueObject> = _collectionNameValueObject

    fun onCategorySelected(category: CollectionCategory) {
        state = state.copy(
            category = category
        )
    }

    fun onNameChange(newName: String) {
        _collectionNameValueObject.value.validate(newName)
    }

    fun onDescriptionChange(newDescription: String) {
        state = state.copy(
            description = newDescription
        )
    }

    fun onIsUrgentChange(newValue: Boolean) {
        state = state.copy(
            isUrgent = newValue
        )
    }

    fun onSave() {
        state = state.copy(
            isLoading = true
        )
        val collection = Collection(
            id = UUID.randomUUID().toString(),
            name = state.name,
            description = state.description,
            category = state.category,
            isUrgent = state.isUrgent,
            tasks = emptyList()
        )
        val savedCollection = runBlocking {
            saveCollectionUseCase.execute(collection)
        }

        savedCollection.fold(
            ifLeft = { failure ->
                state = state.copy(
                    isLoading = false,
                    errorMessage = failure.message
                )
            },
            ifRight = {
                state = state.copy(
                    isLoading = false
                )
            }
        )
    }
}