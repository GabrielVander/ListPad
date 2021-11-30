package vander.gabriel.listpad.data.datasources.models

import com.google.firebase.firestore.DocumentSnapshot

data class CollectionModel(
    var id: String?,
    val name: String,
    val description: String,
    val isUrgent: Boolean,
    val categoryId: String,
) {
    companion object {
        fun toObject(doc: DocumentSnapshot): CollectionModel {
            return CollectionModel(
                id = doc.id,
                name = doc.getString("name") as String,
                description = doc.getString("description") as String,
                categoryId = doc.getString("categoryId") as String,
                isUrgent = doc.getBoolean("isUrgent") as Boolean
            )
        }
    }
}