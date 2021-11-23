package vander.gabriel.listpad.presentation.view_models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.usecases.GetAllCollectionsUseCase
import java.util.Collections.emptyList

/**
 * The default UI state
 */
data class CollectionsState(
    /**
     * A list of collections to be displayed
     */
    val dataToDisplayOnScreen: List<Collection> = emptyList(),
    /**
     * Whether a loading indication should be displayed or not
     */
    val loading: Boolean = false,

    /**
     * Whether an error occurred or not
     */
    val isError: Boolean = false,

    /**
     * The error's message
     */
    val errorMessage: String? = null,

    /**
     * The currently focused collection
     */
    val currentSelectedCollection: Collection? = null,
)

/**
 * The default ViewModel for all Collection-related things
 */
class CollectionsViewModel(
    /**
     * The GetAllCollections use case
     */
    private val getAllCollectionsUseCase: GetAllCollectionsUseCase = GetAllCollectionsUseCase(),
) : ViewModel() {

    /**
     * The default observable state
     */
    var state: CollectionsState by mutableStateOf(
        CollectionsState(
            loading = true
        )
    )

    init {
        val dataToDisplayOnScreen = runBlocking {
            getAllCollectionsUseCase.execute(Unit)
        }
        dataToDisplayOnScreen.fold(
            { state = CollectionsState(isError = true, errorMessage = it.message) },
            { state = CollectionsState(dataToDisplayOnScreen = it) }
        )
    }
}