package dev.yacsa.repository.impl.mapper.formats

import dev.yacsa.database.model.FormatsDbModel
import dev.yacsa.repository.model.FormatsRepoModel
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
)
interface NewFormatsRepoDbMapper {

    fun toRepo(
        formatsDbModel: FormatsDbModel
    ): FormatsRepoModel

    fun toDb(
        formatsRepoModel: FormatsRepoModel
    ): FormatsDbModel

}