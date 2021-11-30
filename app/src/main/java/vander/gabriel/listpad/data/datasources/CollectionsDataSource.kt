package vander.gabriel.listpad.data.datasources

import kotlinx.coroutines.flow.Flow
import vander.gabriel.listpad.data.datasources.models.CollectionModel

/**
 * The DataSource responsible for performing all Collection-related actions
 * on to the external infrastructure
 */
interface CollectionsDataSource {
    fun getAllCollections(): Flow<List<CollectionModel>>
    suspend fun saveCollection(collection: CollectionModel): CollectionModel
}