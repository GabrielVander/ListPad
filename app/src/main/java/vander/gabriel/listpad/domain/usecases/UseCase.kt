package vander.gabriel.listpad.domain.usecases

/**
 * The base UseCase interface to be implemented
 */
interface UseCase<Type, Parameter> {
    /**
     * The default use case executor
     */
    suspend fun execute(parameter: Parameter): Type
}