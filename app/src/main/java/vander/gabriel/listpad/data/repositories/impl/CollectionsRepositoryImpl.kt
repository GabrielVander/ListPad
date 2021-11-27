package vander.gabriel.listpad.data.repositories.impl

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import kotlinx.coroutines.runBlocking
import org.mapstruct.factory.Mappers
import vander.gabriel.listpad.data.datasources.CollectionsDataSource
import vander.gabriel.listpad.data.datasources.impl.CollectionsDataSourceMockImpl
import vander.gabriel.listpad.data.datasources.models.CollectionModel
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.data.repositories.failures.UnexpectedDataSourceFailure
import vander.gabriel.listpad.data.repositories.mappers.CollectionMapper
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.failures.Failure
import java.util.stream.Collectors

class CollectionsRepositoryImpl(
    private val dataSource: CollectionsDataSource = CollectionsDataSourceMockImpl(),
) : CollectionsRepository {

    private val collectionMapper: CollectionMapper = Mappers.getMapper(CollectionMapper::class.java)

    override fun getAllCollections(): Either<Failure, List<Collection>> {
        return try {
            val models = runBlocking { dataSource.getAllCollections() }

            models
                .stream()
                .map(collectionMapper::map)
                .collect(Collectors.toList())
                .right()
        } catch (e: Exception) {
            UnexpectedDataSourceFailure(e.message)
                .left()
        }
    }

    override fun saveCollection(parameter: Collection): Either<Failure, Collection> {
        return try {
            val collectionModel = collectionMapper.map(parameter)

            val result: CollectionModel = runBlocking { dataSource.saveCollection(collectionModel) }

            collectionMapper
                .map(result)
                .right()
        } catch (e: Exception) {
            UnexpectedDataSourceFailure(e.message)
                .left()
        }
    }
}