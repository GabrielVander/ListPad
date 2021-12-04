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
import vander.gabriel.listpad.data.repositories.failures.DocumentNotSavedFailure
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
        return try {
            val allCollectionsFlow: Flow<List<CollectionModel?>> = dataSource.getAllCollections()
            val map: Flow<List<Collection>> = allCollectionsFlow.map { value ->
                value
                    .stream()
                    .filter { collection ->
                        collection != null
                    }
                    .map { collection ->
                        collectionMapper.map(collection!!)
                    }
                    .collect(Collectors.toList())
            }

            map.right()
        } catch (e: Exception) {
            Log.w(tag, "Something went wrong", e)
            UnexpectedDataSourceFailure(e.message)
                .left()
        }
    }

    override fun saveCollection(parameter: Collection): Either<Failure, Collection?> {
        return try {
            val collectionModel = collectionMapper.map(parameter)

            val result: CollectionModel? =
                runBlocking { dataSource.saveCollection(collectionModel) }

            if (result != null) {
                collectionMapper
                    .map(result)
                    .right()
            } else {
                DocumentNotSavedFailure(null).left()
            }
        } catch (e: Exception) {
            Log.w(tag, "Something went wrong", e)
            UnexpectedDataSourceFailure(e.message)
                .left()
        }
    }

    override fun getSingleCollection(collectionId: String): Either<Failure, Flow<Collection>> {
        return try {
            val result: Flow<CollectionModel?> = dataSource.getCollection(collectionId)
            result.map { collectionModel -> collectionMapper.map(collectionModel!!) }.right()
        } catch (e: Exception) {
            Log.w(tag, "Something went wrong", e)
            UnexpectedDataSourceFailure(e.message)
                .left()
        }
    }
}