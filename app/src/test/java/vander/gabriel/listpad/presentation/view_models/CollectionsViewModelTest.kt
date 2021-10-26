package vander.gabriel.listpad.presentation.view_models

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.domain.entities.CollectionEntity

internal class CollectionsViewModelTest {

    @ExperimentalCoroutinesApi
    val dispatcher = TestCoroutineDispatcher()

    var collectionsRepository: CollectionsRepository = mock()
    var collectionsViewModel: CollectionsViewModel =
        CollectionsViewModel(collectionsRepository)

    @ExperimentalCoroutinesApi
    @BeforeEach
    internal fun setUp() {
        Dispatchers.setMain(dispatcher)
        collectionsRepository = mock()
        collectionsViewModel = CollectionsViewModel(collectionsRepository)
    }

    @ExperimentalCoroutinesApi
    @AfterEach
    internal fun tearDown() {
        Dispatchers.resetMain()
    }

    @Nested
    inner class GetCollections {
        @DelicateCoroutinesApi
        @Test
        @DisplayName("Should call repository correctly")
        fun shouldCallRepositoryCorrectly(): Unit = runBlocking {

            whenever(collectionsRepository.getAllCollections())
                .thenAnswer { emptyList<CollectionEntity>() }

            collectionsViewModel.state

            verify(collectionsRepository, times(1)).getAllCollections()
        }
    }
}