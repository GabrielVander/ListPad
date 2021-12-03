package vander.gabriel.listpad.data.datasources.models

data class CollectionModel(
    var id: String? = null,
    var name: String? = null,
    var description: String? = null,
    var urgent: Boolean? = null,
    var categoryId: String? = null,
    var tasks: List<TaskModel>? = null,
)