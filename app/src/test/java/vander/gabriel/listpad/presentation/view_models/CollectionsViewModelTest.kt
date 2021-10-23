package vander.gabriel.listpad.presentation.view_models

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.domain.entities.CollectionEntity

internal class CollectionsViewModelTest {

    var collectionsRepository: CollectionsRepository = mock()
    var collectionsViewModel: CollectionsViewModel =
        CollectionsViewModel(collectionsRepository)

    @BeforeEach
    fun setUp() {
        collectionsRepository = mock()
        collectionsViewModel = CollectionsViewModel(collectionsRepository)
    }

    @Nested
    inner class GetCollections {
        @DelicateCoroutinesApi
        @Test
        @DisplayName("Should call repository correctly")
        fun shouldCallRepositoryCorrectly(): Unit = runBlocking {

            whenever(collectionsRepository.getAllCollections())
                .thenAnswer { emptyList<CollectionEntity>() }

            collectionsViewModel.getCollections()

            verify(collectionsRepository, times(1)).getAllCollections()
        }
    }
}