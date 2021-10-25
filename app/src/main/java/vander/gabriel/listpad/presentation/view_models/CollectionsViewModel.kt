package vander.gabriel.listpad.presentation.view_models

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.domain.entities.CollectionEntity

/**
 * The default ViewModel for all Collection-related things
 */
class CollectionsViewModel(private val collectionsRepository: CollectionsRepository) : ViewModel() {

    init {
        viewModelScope.launch {
            collections = collectionsRepository.getAllCollections()
        }
    }

    /**
     * The entire list of all collections
     */
    var collections: List<CollectionEntity> = mutableStateListOf()
        private set

}