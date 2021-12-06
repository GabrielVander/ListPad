package vander.gabriel.listpad.domain.value_objects

import arrow.core.Either

abstract class ValueObject<T>(open val value: Either<ObjectFailure<T>, T>) {
    abstract fun validate(input: T): ValueObject<T>
}