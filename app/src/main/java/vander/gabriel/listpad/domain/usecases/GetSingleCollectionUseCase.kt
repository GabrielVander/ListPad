package vander.gabriel.listpad.domain.usecases

import android.util.Log
import arrow.core.Either
import arrow.core.left
import kotlinx.coroutines.flow.Flow
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.data.repositories.impl.CollectionsRepositoryImpl
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.usecases.failures.UnexpectedRepositoryFailure
import vander.gabriel.listpad.failures.Failure

class GetSingleCollectionUseCase(
    private val repository: CollectionsRepository = CollectionsRepositoryImpl(),
) : UseCase<Either<Failure, Flow<Collection>>, String> {
    private val tag = "GetAllCollectionsUseCase"

    override fun execute(parameter: String): Either<Failure, Flow<Collection>> {
        Log.i(tag, "Executing...")
        return try {
            repository.getSingleCollection(parameter)
        } catch (e: Exception) {
            UnexpectedRepositoryFailure(e.message).left()
        }
    }
}