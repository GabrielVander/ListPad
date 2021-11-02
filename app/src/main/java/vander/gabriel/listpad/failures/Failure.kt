package vander.gabriel.listpad.failures

/**
 * Base Failure class that all failures should extend
 */
abstract class Failure(
    /**
     * The error's message
     */
    val message: String?,
)