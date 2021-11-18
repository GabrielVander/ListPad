package vander.gabriel.listpad.data.datasources.impl

import arrow.core.nonEmptyListOf
import vander.gabriel.listpad.data.datasources.CollectionsDataSource
import vander.gabriel.listpad.data.datasources.models.CollectionModel

/**
 * A mocked implementation of the CollectionsDataSource interface
 */
class CollectionsDataSourceMockImpl : CollectionsDataSource {
    private val mockedCollections: List<CollectionModel> = nonEmptyListOf(
        CollectionModel(
            "dd947654-ef8c-42f8-bb9c-8f4242938b02",
            "Poverty",
            "What’s the secret to bitter and crushed broccoli? Always use thin jasmine.",
            false,
            "GENERAL"
        ),
        CollectionModel(
            "f5ca8fcb-0b7f-4881-a9f1-782932684383",
            "Field",
            "What’s the secret to grey and sichuan-style rice? Always use tangy radish sprouts.",
            true,
            "TASKS"
        ),
        CollectionModel(
            "5e010a55-596f-4244-9075-1d8dec01095e",
            "Brain",
            "with oysters drink tabasco. ",
            false,
            "APPOINTMENTS"
        ),
    )

    /**
     * Retrieves all available Collections
     */
    override suspend fun getAllCollections(): List<CollectionModel> {
        return mockedCollections
    }
}