package vander.gabriel.listpad.data.datasources.impl
//
//import androidx.compose.runtime.mutableStateListOf
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.callbackFlow
//import vander.gabriel.listpad.data.datasources.CollectionsDataSource
//import vander.gabriel.listpad.data.datasources.models.CollectionModel
//import java.util.*
//
//class CollectionsDataSourceMockImpl : CollectionsDataSource {
//    private val mockedCollections: MutableList<CollectionModel> = mutableStateListOf(
//        CollectionModel(
//            "dd947654-ef8c-42f8-bb9c-8f4242938b02",
//            "Poverty",
//            "What’s the secret to bitter and crushed broccoli? Always use thin jasmine.",
//            false,
//            "GENERAL"
//        ),
//        CollectionModel(
//            "f5ca8fcb-0b7f-4881-a9f1-782932684383",
//            "Field",
//            "What’s the secret to grey and sichuan-style rice? Always use tangy radish sprouts.",
//            true,
//            "TASKS"
//        ),
//        CollectionModel(
//            "5e010a55-596f-4244-9075-1d8dec01095e",
//            "Brain",
//            "with oysters drink tabasco. ",
//            false,
//            "APPOINTMENTS"
//        ),
//    )
//
//    override suspend fun getAllCollections(): Flow<List<CollectionModel>> = callbackFlow {
//        return trySend(mockedCollections.toList())
//    }
//
//    override suspend fun saveCollection(collection: CollectionModel): CollectionModel {
//        val newCollection = CollectionModel(
//            id = UUID.randomUUID().toString(),
//            name = collection.name,
//            description = collection.description,
//            categoryId = collection.categoryId,
//            isUrgent = collection.isUrgent
//        )
//
//        this.mockedCollections.add(newCollection)
//
//        return newCollection
//    }
//}