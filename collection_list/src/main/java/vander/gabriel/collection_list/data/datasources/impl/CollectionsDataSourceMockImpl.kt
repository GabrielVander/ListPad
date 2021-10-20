package vander.gabriel.collection_list.data.datasources.impl

import vander.gabriel.collection_list.data.datasources.CollectionsDataSource
import vander.gabriel.collection_list.data.datasources.models.CollectionModel

/**
 * A mocked implementation of the CollectionsDataSource interface
 */
class CollectionsDataSourceMockImpl : CollectionsDataSource {
    private val mockedCollections: List<CollectionModel> = listOf(
        CollectionModel(
            "dd947654-ef8c-42f8-bb9c-8f4242938b02",
            "poverty",
            "bad divide alive meantime property",
            false
        ),
        CollectionModel(
            "f5ca8fcb-0b7f-4881-a9f1-782932684383",
            "field",
            "replace king sheet female true",
            true
        ),
        CollectionModel(
            "5e010a55-596f-4244-9075-1d8dec01095e",
            "brain",
            "mad pretense dry pressure wage",
            false
        ),
    )

    /**
     * Retrieves all available Collections
     */
    override suspend fun getAllCollections(): List<CollectionModel> {
        return mockedCollections
    }
}