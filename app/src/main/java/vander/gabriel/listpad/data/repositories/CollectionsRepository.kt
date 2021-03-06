package vander.gabriel.listpad.data.repositories

import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.failures.Failure

interface CollectionsRepository {

    fun getAllCollections(): Either<Failure, Flow<List<Collection>>>
    fun saveCollection(parameter: Collection): Either<Failure, Collection?>
    fun getSingleCollection(collectionId: String): Either<Failure, Flow<Collection>>
    fun updateCollection(collection: Collection): Either<Failure, Collection>
    fun deleteCollection(collectionId: String): Either<Failure, Unit>
}