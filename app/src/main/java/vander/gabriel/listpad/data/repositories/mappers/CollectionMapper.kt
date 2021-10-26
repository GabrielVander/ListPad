package vander.gabriel.listpad.data.repositories.mappers

import org.mapstruct.Mapper
import vander.gabriel.listpad.data.datasources.models.CollectionModel
import vander.gabriel.listpad.domain.entities.CollectionEntity

/**
 * A basic CollectionMapper with MapStruct
 */
@Mapper
interface CollectionMapper {

    /**
     * Converts from a CollectionModel to a CollectionEntity
     */
    fun fromModelToEntity(model: CollectionModel): CollectionEntity

}