package vander.gabriel.listpad.data.repositories.impl

import vander.gabriel.listpad.data.datasources.CollectionsDataSource
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.domain.entities.CollectionEntity

/**
 * The default CollectionsDataSource implementation
 */
class CollectionsRepositoryImpl(private val dataSource: CollectionsDataSource) :
    CollectionsRepository {

    /**
     * Retrieves all Collections available
     */
    override suspend fun getAllCollections(): List<CollectionEntity> {
        TODO("Not yet implemented")
    }
}