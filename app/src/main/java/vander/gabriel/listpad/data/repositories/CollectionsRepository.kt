package vander.gabriel.listpad.data.repositories

import vander.gabriel.listpad.domain.entities.Collection

/**
 * The repository responsible for all Collection-related actions
 */
interface CollectionsRepository {

    /**
     * Retrieves all Collections available
     */
    fun getAllCollections(): List<Collection>
}