package vander.gabriel.listpad.domain.entities

/**
 * An enumeration that categorizes a collection based on it's 'theme'
 */
enum class CollectionCategory {
    /**
     * General task-related collection
     */
    TASKS,

    /**
     * Shopping-related collection
     */
    SHOPPING,

    /**
     * General collection for appointments
     */
    APPOINTMENTS,

    /**
     * The most default, generic collection
     */
    GENERAL;
}