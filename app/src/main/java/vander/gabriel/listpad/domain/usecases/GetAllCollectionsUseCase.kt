package vander.gabriel.listpad.domain.usecases

import arrow.core.Either
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.data.repositories.impl.CollectionsRepositoryImpl
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.failures.Failure

/**
 * Returns all collections
 */
class GetAllCollectionsUseCase(
    /**
     * The Collections repository
     */
    private val repository: CollectionsRepository = CollectionsRepositoryImpl(),
) : UseCase<Either<Failure, List<Collection>>, Unit> {
    /**
     * The default use case executor
     */
    override suspend fun execute(parameter: Unit): Either<Failure, List<Collection>> {
        return repository.getAllCollections()
    }
}