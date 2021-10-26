package vander.gabriel.listpad.domain.entities

import androidx.compose.ui.graphics.Color

/**
 * An enumeration that categorizes a collection based on it's 'theme'
 */
enum class CollectionCategory(val color: Color) {
    /**
     * General task-related collection
     */
    TASKS(Color.Green),

    /**
     * Shopping-related collection
     */
    SHOPPING(Color.Yellow),

    /**
     * General collection for appointments
     */
    APPOINTMENTS(Color.Cyan),

    /**
     * The most default, generic collection
     */
    GENERAL(Color.Gray);
}