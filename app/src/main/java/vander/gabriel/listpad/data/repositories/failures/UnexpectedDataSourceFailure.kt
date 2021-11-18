package vander.gabriel.listpad.data.repositories.failures

import vander.gabriel.listpad.failures.Failure

/**
 * A datasource-related unexpected failure
 */
class UnexpectedDataSourceFailure(message: String?) : Failure(message)