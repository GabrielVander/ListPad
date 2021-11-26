package vander.gabriel.listpad.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class CollectionCreationState(
    val name: String,
    val description: String,
    val isUrgent: Boolean = false,
)

class CollectionCreationViewModel : ViewModel() {

    var state: CollectionCreationState by mutableStateOf(
        CollectionCreationState(
            name = "",
            description = "",
            isUrgent = false,
        )
    )
}