package vander.gabriel.listpad.data.repositories.impl

import org.mapstruct.factory.Mappers
import vander.gabriel.listpad.data.datasources.CollectionsDataSource
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.data.repositories.mappers.CollectionMapper
import vander.gabriel.listpad.domain.entities.CollectionEntity
import java.util.stream.Collectors

/**
 * The default CollectionsDataSource implementation
 */
class CollectionsRepositoryImpl(
    private val dataSource: CollectionsDataSource,
) :
    CollectionsRepository {

    private val collectionMapper: CollectionMapper = Mappers.getMapper(CollectionMapper::class.java)

    /**
     * Retrieves all Collections available
     */
    override suspend fun getAllCollections(): List<CollectionEntity> {
        val models = dataSource.getAllCollections()
        return models
            .stream()
            .map(collectionMapper::fromModelToEntity)
            .collect(Collectors.toList())
    }
}