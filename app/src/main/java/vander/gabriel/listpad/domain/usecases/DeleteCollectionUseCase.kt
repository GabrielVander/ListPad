package vander.gabriel.listpad.domain.usecases

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.data.repositories.impl.CollectionsRepositoryImpl
import vander.gabriel.listpad.domain.usecases.failures.UnexpectedRepositoryFailure
import vander.gabriel.listpad.failures.Failure

class DeleteCollectionUseCase(
    private val repository: CollectionsRepository = CollectionsRepositoryImpl(),
) : UseCase<Either<Failure, Unit>, String> {
    override fun execute(parameter: String): Either<Failure, Unit> {
        return try {
            repository
                .deleteCollection(parameter)
            Unit.right()
        } catch (e: Exception) {
            UnexpectedRepositoryFailure(e.message).left()
        }
    }
}
