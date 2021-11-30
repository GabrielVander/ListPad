package vander.gabriel.listpad.presentation.view_models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.usecases.GetAllCollectionsUseCase
import java.util.Collections.emptyList


@InternalCoroutinesApi
class CollectionsViewModel(
    private val getAllCollectionsUseCase: GetAllCollectionsUseCase = GetAllCollectionsUseCase(),
) : ViewModel() {

    private val _loading: MutableState<Boolean> = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    private val _isError: MutableState<Boolean> = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _errorMessage: MutableState<String?> = mutableStateOf(null)
    val errorMessage: State<String?> = _errorMessage

    private val _collectionsStateFlow: MutableStateFlow<List<Collection>> =
        MutableStateFlow(emptyList())
    val collectionsStateFlow: StateFlow<List<Collection>> = _collectionsStateFlow

    @InternalCoroutinesApi
    fun updateCollectionList() {
        _loading.value = true
        val dataToDisplayOnScreen =
            getAllCollectionsUseCase.execute(Unit)

        dataToDisplayOnScreen.fold(
            ifLeft = { failure ->
                _isError.value = true
                _errorMessage.value = failure.message
            },
            ifRight = { flow ->
                viewModelScope.launch {
                    flow.collect {
                        _collectionsStateFlow.value = it
                    }
                }
            }
        )

        _loading.value = false

    }
}