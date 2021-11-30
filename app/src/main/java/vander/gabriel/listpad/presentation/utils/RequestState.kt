package vander.gabriel.listpad.presentation.utils

import vander.gabriel.listpad.failures.Failure

sealed class RequestState<out T> {
    object Idle : RequestState<Nothing>()
    object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val failure: Failure) : RequestState<Nothing>()
}
