package vander.gabriel.listpad.data.repositories.impl

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import vander.gabriel.listpad.data.datasources.CollectionsDataSource
import vander.gabriel.listpad.data.datasources.models.CollectionModel
import vander.gabriel.listpad.data.repositories.CollectionsRepository

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
                listOf<CollectionModel>()
            }

            repository.getAllCollections()

            verify(datasource, times(1)).getAllCollections()
        }

    }
}