package vander.gabriel.listpad.data.datasources.impl

import android.util.Log
import com.google.firebase.firestore.BuildConfig
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import vander.gabriel.listpad.data.datasources.CollectionsDataSource
import vander.gabriel.listpad.data.datasources.exceptions.DocumentNotFoundException
import vander.gabriel.listpad.data.datasources.models.CollectionModel

class FirebaseDataSourceImpl : CollectionsDataSource {
    private val tag = "FirebaseDataSource"
    private val firestore = FirebaseFirestore.getInstance()

    override fun getAllCollections(): Flow<List<CollectionModel?>> = callbackFlow {
        val collection = firestore.collection("collections")
        val snapshotListener = collection.addSnapshotListener { value, error ->
            val response = if (error == null && value != null) {
                val documents = value.documents
                Log.i(tag, "Retrieved ${documents.size} documents")
                documents.map { documentSnapshot -> documentSnapshot.toObject<CollectionModel>() }
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

    override fun saveCollection(collection: CollectionModel): CollectionModel {
        val tag = "FirebaseDataSourceImpl.saveCollection"
        val collectionReference = firestore.collection("collections")

        collection.id?.let {
            if (BuildConfig.DEBUG) {
                Log.i(tag, "Saving collection with id ${collection.id}")
            }
            collectionReference.document(it)
                .set(collection).result
        }

        return collection
    }

    override fun getCollection(collectionId: String): Flow<CollectionModel?> = callbackFlow {
        val tag = "FirebaseDataSourceImpl.getCollection"
        val collectionReference = firestore.collection("collections")

        val snapshotListener = collectionReference.document(collectionId)
            .addSnapshotListener { snapshot, error ->
                if (error == null && snapshot != null) {
                    Log.i(tag, "Retrieved document with id $collectionId")

                    trySendBlocking(snapshot.toObject<CollectionModel>()
                        ?: throw DocumentNotFoundException(
                            collectionId))

                } else {
                    Log.w(tag,
                        "Something went wrong while attempting " +
                                "to retrieve collection with id $collectionId:\n$error")
                    throw DocumentNotFoundException(collectionId)
                }

            }
        awaitClose {
            snapshotListener.remove()
        }
    }
}