package dev.yacsa.repository.impl.mapper.person

import dev.yacsa.database.model.PersonDbModel
import dev.yacsa.repository.model.PersonRepoModel
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
)
interface NewPersonRepoDbMapper {

    fun toRepo(
        personDbModel: PersonDbModel
    ): PersonRepoModel

    fun toDb(
        personRepoModel: PersonRepoModel
    ): PersonDbModel

}