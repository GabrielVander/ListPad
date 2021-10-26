package vander.gabriel.listpad.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.data.repositories.impl.CollectionsRepositoryImpl
import vander.gabriel.listpad.domain.entities.CollectionEntity

/**
 * The default UI state
 */
data class CollectionsState(
    /**
     * A list of collections to be displayed
     */
    val dataToDisplayOnScreen: List<CollectionEntity> = emptyList(),
    /**
     * Whether a loading indication should be displayed or not
     */
    val loading: Boolean = false
)

/**
 * The default ViewModel for all Collection-related things
 */
class CollectionsViewModel(
    collectionsRepository: CollectionsRepository = CollectionsRepositoryImpl()
) : ViewModel() {

    /**
     * Loads the collection list initially
     */
    private var initialCollections = collectionsRepository.getAllCollections()

    /**
     * The default observable state
     */
    var state: CollectionsState by mutableStateOf(
        CollectionsState(
            dataToDisplayOnScreen = initialCollections
        )
    )
}