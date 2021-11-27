package vander.gabriel.listpad.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.usecases.GetAllCollectionsUseCase
import java.util.Collections.emptyList

data class CollectionsState(
    val dataToDisplayOnScreen: List<Collection> = emptyList(),
    val loading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val currentSelectedCollection: Collection? = null,
)

class CollectionsViewModel(
    private val getAllCollectionsUseCase: GetAllCollectionsUseCase = GetAllCollectionsUseCase(),
) : ViewModel() {

    var state: CollectionsState by mutableStateOf(
        CollectionsState(
            loading = true
        )
    )

    init {
        updateCollectionList()
    }

    fun updateCollectionList() {
        val dataToDisplayOnScreen = runBlocking {
            getAllCollectionsUseCase.execute(Unit)
        }
        dataToDisplayOnScreen.fold(
            { state = CollectionsState(isError = true, errorMessage = it.message) },
            { state = CollectionsState(dataToDisplayOnScreen = it) }
        )
    }
}