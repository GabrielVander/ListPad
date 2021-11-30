package vander.gabriel.listpad.data.datasources.impl

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import vander.gabriel.listpad.data.datasources.CollectionsDataSource
import vander.gabriel.listpad.data.datasources.models.CollectionModel

class FirebaseDataSourceImpl : CollectionsDataSource {
    private val tag = "FirebaseDataSource"
    private val firestore = FirebaseFirestore.getInstance()

    override fun getAllCollections(): Flow<List<CollectionModel>> = callbackFlow {
        val collection = firestore.collection("collections")
        val snapshotListener = collection.addSnapshotListener { value, error ->
            val response = if (error == null && value != null) {
                val documents = value.documents
                Log.i(tag, "Retrieved ${documents.size} documents")
                documents.map { documentSnapshot -> CollectionModel.toObject(documentSnapshot) }
            } else {
                Log.w(tag,
                    "Something went wrong while attempting " +
                            "to retrieve collections data:\n$error")
                emptyList()
            }

            trySendBlocking(response)
        }

        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun saveCollection(collection: CollectionModel): CollectionModel {
        val collectionReference = firestore.collection("collections")

        val documentSnapshot = collectionReference.add(collection).result?.get()?.result

        return CollectionModel(
            id = documentSnapshot?.id,
            name = documentSnapshot?.get("name") as String,
            description = documentSnapshot.get("description") as String,
            categoryId = documentSnapshot.get("categoryId") as String,
            isUrgent = documentSnapshot.get("isUrgent") as Boolean,
        )
    }
}