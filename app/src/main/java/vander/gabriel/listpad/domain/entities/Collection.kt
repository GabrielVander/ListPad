package vander.gabriel.listpad.domain.entities

data class Collection(
    val id: String,
    val name: String,
    val description: String,
    val isUrgent: Boolean,
    val category: CollectionCategory = CollectionCategory.GENERAL,
    val tasks: List<Task> = emptyList(),
)
