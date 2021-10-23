package vander.gabriel.listpad.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.domain.entities.CollectionEntity

/**
 * The default ViewModel for all Collection-related things
 */
class CollectionsViewModel(private val collectionsRepository: CollectionsRepository) : ViewModel() {
    @DelicateCoroutinesApi
    private val collections: MutableLiveData<List<CollectionEntity>> by lazy {
        MutableLiveData<List<CollectionEntity>>().also {
            loadCollections()
        }
    }

    /**
     * Returns all current collections
     */
    @DelicateCoroutinesApi
    fun getCollections(): LiveData<List<CollectionEntity>> {
        return collections
    }

    @DelicateCoroutinesApi
    private fun loadCollections() {
        GlobalScope.launch {
            val fetchedCollections = collectionsRepository.getAllCollections()

            collections.postValue(fetchedCollections)
        }
    }

}