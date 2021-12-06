package vander.gabriel.listpad.domain.usecases.failures

import vander.gabriel.listpad.failures.Failure

/**
 * A repository-related unexpected failure
 */
class UnexpectedRepositoryFailure(message: String?) : Failure(message)