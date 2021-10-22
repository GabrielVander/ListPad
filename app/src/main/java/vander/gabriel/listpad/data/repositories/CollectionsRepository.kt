package vander.gabriel.listpad.data.repositories

import vander.gabriel.listpad.domain.entities.CollectionEntity

/**
 * The repository responsible for all Collection-related actions
 */
interface CollectionsRepository {

    /**
     * Retrieves all Collections available
     */
    suspend fun getAllCollections(): List<CollectionEntity>
}