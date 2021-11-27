package vander.gabriel.listpad.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import vander.gabriel.listpad.domain.entities.CollectionCategory

data class CollectionCreationState(
    val name: String,
    val description: String,
    val category: CollectionCategory,
    val isUrgent: Boolean = false,
    val isLoading: Boolean = false,
)

class CollectionCreationViewModel : ViewModel() {
    fun onCategorySelected(category: CollectionCategory) {
        state = state.copy(
            category = category
        )
    }

    fun onNameChange(newName: String) {
        state = state.copy(
            name = newName
        )
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
        state = state.copy(
            isLoading = false
        )
    }

    var state: CollectionCreationState by mutableStateOf(
        CollectionCreationState(
            name = "",
            description = "",
            category = CollectionCategory.GENERAL,
            isUrgent = false,
        )
    )
}