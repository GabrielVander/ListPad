package vander.gabriel.listpad.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.usecases.GetAllCollectionsUseCase
import vander.gabriel.listpad.domain.usecases.GetSingleCollectionUseCase
import vander.gabriel.listpad.presentation.utils.RequestState


@InternalCoroutinesApi
class CollectionsViewModel(
    private val getAllCollectionsUseCase: GetAllCollectionsUseCase
    = GetAllCollectionsUseCase(),
    private val getSingleCollectionUseCase: GetSingleCollectionUseCase
    = GetSingleCollectionUseCase(),
) : ViewModel() {
    private val _collectionsStateFlow: MutableStateFlow<RequestState<List<Collection>>> =
        MutableStateFlow(RequestState.Idle)
    val collectionsStateFlow: StateFlow<RequestState<List<Collection>>> = _collectionsStateFlow

    private val _singleCollectionStateFlow: MutableStateFlow<RequestState<Collection>> =
        MutableStateFlow(RequestState.Idle)
    val singleCollectionStateFlow: StateFlow<RequestState<Collection>> =
        _singleCollectionStateFlow

    @InternalCoroutinesApi
    fun updateCollectionList() {
        _collectionsStateFlow.value = RequestState.Loading

        val dataToDisplayOnScreen =
            getAllCollectionsUseCase.execute(Unit)

        dataToDisplayOnScreen.fold(
            ifLeft = { failure ->
                _collectionsStateFlow.value = RequestState.Error(failure)
            },
            ifRight = { flow ->
                viewModelScope.launch {
                    flow.collect {
                        _collectionsStateFlow.value = RequestState.Success(it)
                    }
                }
            }
        )
    }

    fun getCollection(collectionId: String) {
        _singleCollectionStateFlow.value = RequestState.Loading

        val dataToDisplayOnScreen =
            getSingleCollectionUseCase.execute(collectionId)

        dataToDisplayOnScreen.fold(
            ifLeft = { failure ->
                _singleCollectionStateFlow.value = RequestState.Error(failure)
            },
            ifRight = { flow ->
                viewModelScope.launch {
                    flow.collect { collection ->
                        _singleCollectionStateFlow.value = RequestState.Success(collection)
                    }
                }
            }
        )
    }
}