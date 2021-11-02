package vander.gabriel.listpad.domain.usecases

import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.data.repositories.impl.CollectionsRepositoryImpl
import vander.gabriel.listpad.domain.entities.Collection

/**
 * Returns all collections
 */
class GetAllCollectionsUseCase(
    /**
     * The Collections repository
     */
    private val repository: CollectionsRepository = CollectionsRepositoryImpl(),
) : UseCase<List<Collection>, Unit> {
    /**
     * The default use case executor
     */
    override suspend fun execute(parameter: Unit): List<Collection> {
        return repository.getAllCollections()
    }
}