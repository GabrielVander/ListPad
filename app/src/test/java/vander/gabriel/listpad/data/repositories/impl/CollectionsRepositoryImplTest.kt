package vander.gabriel.listpad.data.repositories.impl

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import vander.gabriel.listpad.data.datasources.CollectionsDataSource
import vander.gabriel.listpad.data.datasources.models.CollectionModel
import vander.gabriel.listpad.data.repositories.CollectionsRepository
import vander.gabriel.listpad.data.repositories.failures.UnexpectedDataSourceFailure

class UnexpectedException(message: String?) : Exception(message)

internal class CollectionsRepositoryImplTest {
    var datasource: CollectionsDataSource = mock()
    var repository: CollectionsRepository = CollectionsRepositoryImpl(datasource)

    @BeforeEach
    fun setUp() {
        datasource = mock()
        repository = CollectionsRepositoryImpl(datasource)
    }

    @Nested
    @DisplayName("getAllCollections")
    inner class GetAllCollections {

        @Test
        @DisplayName("Should call DataSource correctly")
        fun shouldCallDataSourceCorrectly(): Unit = runBlocking {
            whenever(datasource.getAllCollections()).thenAnswer {
                emptyList<CollectionModel>()
            }

            repository.getAllCollections()

            verify(datasource, times(1)).getAllCollections()
        }

        @Test
        @DisplayName("Should return an UnexpectedDataSourceFailure")
        fun shouldReturnAnUnexpectedDataSourceFailure(): Unit = runBlocking {

            whenever(datasource.getAllCollections())
                .doAnswer { throw UnexpectedException("Unexpected exception") }

            val result = repository.getAllCollections()

            assertNotNull(result)
            assertTrue(result.isLeft())
            result.tapLeft {
                assertTrue(it is UnexpectedDataSourceFailure)
            }
        }

    }
}