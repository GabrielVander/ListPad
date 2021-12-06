package vander.gabriel.listpad.domain.value_objects

import arrow.core.Either
import vander.gabriel.listpad.domain.usecases.CheckCollectionUniquenessUseCase

data class CollectionNameValueObject(
    override var value: Either<ObjectFailure<String>, String> = Either.Left(NotValidated()),
) : ValueObject<String>(value) {
    companion object {
        class NotValidated :
            ObjectFailure<String>("")

        data class EmptyObjectFailure(val failedValue: String) :
            ObjectFailure<String>(failedValue)

        data class NotUniqueObjectFailure(val failedValue: String) :
            ObjectFailure<String>(failedValue)
    }

    private val checkCollectionUniquenessUseCase: CheckCollectionUniquenessUseCase =
        CheckCollectionUniquenessUseCase()

    override fun validate(input: String): CollectionNameValueObject {
        if (input.isEmpty()) return CollectionNameValueObject(Either.Left(EmptyObjectFailure("")))

        val isUnique = checkCollectionUniquenessUseCase.execute(input)
        if (!isUnique) return CollectionNameValueObject(Either.Left(NotUniqueObjectFailure(input)))

        return CollectionNameValueObject(Either.Right(input))
    }
}

