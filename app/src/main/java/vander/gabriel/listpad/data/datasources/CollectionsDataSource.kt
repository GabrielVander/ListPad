package vander.gabriel.listpad.data.datasources

import kotlinx.coroutines.flow.Flow
import vander.gabriel.listpad.data.datasources.models.CollectionModel

interface CollectionsDataSource {
    fun getAllCollections(): Flow<List<CollectionModel?>>
    fun saveCollection(collection: CollectionModel): CollectionModel?
    fun getCollection(collectionId: String): Flow<CollectionModel?>
}