package vander.gabriel.listpad.data.datasources.models

/**
 * A class representation of the data as stored in the database
 */
data class CollectionModel(
    /**
     * The document's unique identifier in the database
     */
    val id: String,
    /**
     *  The name field value received from the database
     */
    val name: String,
    /**
     * The description field value received from the database
     */
    val description: String,
    /**
     * The isUrgent flag value received from the database
     */
    val isUrgent: Boolean
)