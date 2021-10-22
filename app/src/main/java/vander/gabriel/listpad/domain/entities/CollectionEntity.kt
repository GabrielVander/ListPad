package vander.gabriel.listpad.domain.entities

/**
 * A CollectionEntity is a group of related items
 */
data class CollectionEntity(
    /**
     * The unique identifier of this entity
     */
    val id: String,
    /**
     * An unique name that identifies the collection
     */
    val name: String,
    /**
     * A short description that describes what the collection is about
     */
    val description: String,
    /**
     * Whether the collection is urgent - important - or not
     */
    val isUrgent: Boolean
)
