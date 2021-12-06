package vander.gabriel.listpad.data.datasources.impl

import android.util.Log
import com.google.firebase.firestore.BuildConfig
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
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
    private val collectionReference = firestore.collection("collections")


    override fun getAllCollections(): Flow<List<CollectionModel?>> = callbackFlow {
        val snapshotListener = collectionReference.addSnapshotListener { value, error ->
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
        collection.id?.let {
            if (BuildConfig.DEBUG) {
                Log.i(tag, "Saving document with id ${collection.id}")
            }
            collectionReference.document(it)
                .set(collection).result
        }

        return collection
    }

    override fun getCollection(collectionId: String): Flow<CollectionModel?> = callbackFlow {
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

    override fun updateCollection(collection: CollectionModel): CollectionModel {
        if (BuildConfig.DEBUG) {
            Log.i(tag, "Updating document with id ${collection.id}")
        }

        if (collection.id != null) {
            collectionReference
                .document(collection.id!!)
                .set(collection, SetOptions.merge())
                .addOnSuccessListener {
                    if (BuildConfig.DEBUG) {
                        Log.i(tag, "Document with id ${collection.id} updated successfully")
                    }
                }
                .addOnFailureListener { exception ->
                    if (BuildConfig.DEBUG) {
                        Log.i(tag, "Document with id ${collection.id} failed to update", exception)
                    }
                }
        }

        return collection
    }

    override fun deleteCollection(collectionId: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, "Deleting document with id $collectionId")
        }

        collectionReference
            .document(collectionId)
            .delete()
            .addOnSuccessListener {
                if (BuildConfig.DEBUG) {
                    Log.i(tag, "Document with id $collectionId updated successfully")
                }
            }
            .addOnFailureListener { exception ->
                if (BuildConfig.DEBUG) {
                    Log.i(tag, "Document with id $collectionId failed to update", exception)
                }
            }
    }

}