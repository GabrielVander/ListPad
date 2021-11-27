package vander.gabriel.listpad.data.datasources.models

data class CollectionModel(

    val id: String?,
    val name: String,
    val description: String,
    val isUrgent: Boolean,
    val categoryId: String,
)