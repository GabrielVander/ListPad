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
)

class CollectionCreationViewModel : ViewModel() {
    fun onCategorySelected(category: CollectionCategory) {
        state = state.copy(
            category = category
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