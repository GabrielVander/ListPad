package vander.gabriel.listpad.data.repositories.impl

import android.util.Log
import arrow.core.Either
import arrow.core.left
import arrow.core.right
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import org.mapstruct.factory.Mappers
import vander.gabriel.listpad.data.datasources.CollectionsDataSource
import vander.gabriel.listpad.data.datasources.impl.FirebaseDataSourceImpl
import vander.gabriel.listpad.data.datasources.models.CollectionModel
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.data.repositories.failures.UnexpectedDataSourceFailure
import vander.gabriel.listpad.data.repositories.mappers.CollectionMapper
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.failures.Failure
import java.util.stream.Collectors

class CollectionsRepositoryImpl(
    private val dataSource: CollectionsDataSource = FirebaseDataSourceImpl(),
) : CollectionsRepository {

    private val tag = "CollectionsRepository"
    private val collectionMapper: CollectionMapper = Mappers.getMapper(CollectionMapper::class.java)

    override fun getAllCollections(): Either<Failure, Flow<List<Collection>>> {
        Log.i(tag, "Retrieving collections from datasource")
        try {
            val allCollectionsFlow: Flow<List<CollectionModel>> = dataSource.getAllCollections()
            val map: Flow<List<Collection>> = allCollectionsFlow.map { value ->
                value
                    .stream()
                    .map { collection -> collectionMapper.map(collection) }
                    .collect(Collectors.toList())
            }

            return map.right()
        } catch (e: Exception) {
            Log.w(tag, "Something went wrong", e)
            return UnexpectedDataSourceFailure(e.message)
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