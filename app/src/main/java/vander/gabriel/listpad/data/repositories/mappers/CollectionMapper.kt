package vander.gabriel.listpad.data.repositories.mappers

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import vander.gabriel.listpad.data.datasources.models.CollectionModel
import vander.gabriel.listpad.domain.entities.Collection

/**
 * A basic CollectionMapper with MapStruct
 */
@Mapper
interface CollectionMapper {

    /**
     * Converts a CollectionModel into a Collection
     */
    @Mappings(
        Mapping(source = "urgent", target = "isUrgent"),
        Mapping(source = "categoryId", target = "category")
    )
    fun map(model: CollectionModel): Collection

//    /**
//     * Converts a categoryId (Int) into a CollectionCategory
//     */
//    public fun map(categoryId: String): CollectionCategory {
//        val category = CollectionCategory.getFromId(categoryId)
//        return category ?: CollectionCategory.GENERAL
//    }

}