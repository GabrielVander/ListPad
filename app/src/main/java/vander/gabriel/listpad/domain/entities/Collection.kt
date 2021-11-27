package vander.gabriel.listpad.domain.entities

/**
 * A Collection is a group of related items
 */
data class Collection(
    /**
     * The unique identifier of this entity
     */
    val id: String? = null,
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
    val isUrgent: Boolean,

    /**
     * The collection's overall 'theme'
     */
    val category: CollectionCategory = CollectionCategory.GENERAL,
)
