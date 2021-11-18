package vander.gabriel.listpad.data.repositories

import arrow.core.Either
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.failures.Failure

/**
 * The repository responsible for all Collection-related actions
 */
interface CollectionsRepository {

    /**
     * Retrieves all Collections available
     */
    fun getAllCollections(): Either<Failure, List<Collection>>
}