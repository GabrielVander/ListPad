package vander.gabriel.listpad.domain.usecases

import arrow.core.right
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.domain.entities.Collection
import vander.gabriel.listpad.domain.usecases.failures.UnexpectedRepositoryFailure
import java.util.Collections.emptyList

class UnexpectedException(message: String?) : Exception(message)

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
        whenever(collectionsRepository.getAllCollections()).thenAnswer {
            emptyList<Collection>()
                .right()
        }

        runBlocking { useCase.execute(Unit) }

        verify(collectionsRepository, times(1)).getAllCollections()
    }

    @Test
    @DisplayName("Should return UnexpectedRepositoryFailure")
    fun shouldReturnUnexpectedRepositoryFailure() {

        whenever(collectionsRepository.getAllCollections()).doAnswer {
            throw UnexpectedException(null)
        }

        val result = runBlocking { useCase.execute(Unit) }

        assertNotNull(result)
        assertTrue(result.isLeft())
        result.tapLeft { assertTrue(it is UnexpectedRepositoryFailure) }
    }
}