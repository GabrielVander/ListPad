package vander.gabriel.collection_list.domain.models

/**
 * A Collection is a group of related items
 */
data class Collection(
    /**
     * The unique identifier of this model
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
