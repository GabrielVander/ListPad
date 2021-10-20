package vander.gabriel.collection_list.data.datasources

import vander.gabriel.collection_list.data.datasources.models.CollectionModel

/**
 * The DataSource responsible for performing all Collection-related actions
 * on to the external infrastructure
 */
interface CollectionsDataSource {
    /**
     * Retrieves all available Collections
     */
    suspend fun getAllCollections(): List<CollectionModel>
}