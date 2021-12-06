package vander.gabriel.listpad.domain.usecases

import android.util.Log
import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.data.repositories.impl.CollectionsRepositoryImpl
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.failures.Failure

class CheckCollectionUniquenessUseCase(
    private val repository: CollectionsRepository = CollectionsRepositoryImpl(),
) : UseCase<Boolean, String> {

    private val tag = "CheckCollectionUniquenessUseCase"

    override fun execute(parameter: String): Boolean {
        return try {
            val result: Either<Failure, Flow<Collection>> =
                repository.checkIfCollectionExists(parameter)

            return result
                .fold(
                    ifRight = { true },
                    ifLeft = { false }
                )
        } catch (e: Exception) {
            Log.e(tag, "Something went wrong", e)
            false
        }
    }
}
