package vander.gabriel.listpad.data.repositories.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import vander.gabriel.listpad.data.datasources.models.CollectionModel
import vander.gabriel.listpad.domain.entities.Collection

@Mapper
interface CollectionMapper {

    @Mappings(
        Mapping(source = "urgent", target = "isUrgent"),
        Mapping(source = "categoryId", target = "category")
    )
    fun map(model: CollectionModel): Collection

    @Mappings(
        Mapping(source = "category", target = "categoryId")
    )
    fun map(entity: Collection): CollectionModel

}