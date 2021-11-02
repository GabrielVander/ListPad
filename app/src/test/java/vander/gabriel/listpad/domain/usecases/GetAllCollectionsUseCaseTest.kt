package vander.gabriel.listpad.domain.usecases

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.domain.entities.Collection

internal class GetAllCollectionsUseCaseTest {

    private lateinit var useCase: GetAllCollectionsUseCase
    private lateinit var collectionsRepository: CollectionsRepository

    @BeforeEach
    fun setUp() {
        collectionsRepository = mock()
        useCase = GetAllCollectionsUseCase(collectionsRepository)
    }

    @Test
    @DisplayName("Should call repository correctly")
    fun shouldCallRepositoryCorrectly() {
        whenever(collectionsRepository.getAllCollections()).thenAnswer { emptyList<Collection>() }

        runBlocking { useCase.execute(Unit) }

        verify(collectionsRepository, times(1)).getAllCollections()
    }
}