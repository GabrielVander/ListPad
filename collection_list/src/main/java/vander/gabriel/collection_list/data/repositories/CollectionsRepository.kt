package vander.gabriel.collection_list.data.repositories

import vander.gabriel.collection_list.domain.entities.CollectionEntity

/**
 * The repository responsible for all Collection-related actions
 */
interface CollectionsRepository {

    /**
     * Retrieves all Collections available
     */
    suspend fun getAllCollections(): List<CollectionEntity>
}