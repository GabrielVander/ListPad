package vander.gabriel.listpad.domain.usecases

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.data.repositories.impl.CollectionsRepositoryImpl
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.usecases.failures.UnexpectedRepositoryFailure
import vander.gabriel.listpad.failures.Failure

class UpdateCollectionUseCase(
    private val repository: CollectionsRepository = CollectionsRepositoryImpl(),
) : UseCase<Either<Failure, Collection>, Collection> {
    override fun execute(parameter: Collection): Either<Failure, Collection> {
        return try {
            val result: Either<Failure, Collection> = repository.updateCollection(parameter)
            return result
                .fold(
                    ifRight = { it.right() },
                    ifLeft = { it.left() }
                )
        } catch (e: Exception) {
            UnexpectedRepositoryFailure(e.message).left()
        }
    }
}
