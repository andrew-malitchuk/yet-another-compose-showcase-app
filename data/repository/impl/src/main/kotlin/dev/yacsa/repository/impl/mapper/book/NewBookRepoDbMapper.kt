package dev.yacsa.repository.impl.mapper.book

import dev.yacsa.database.model.BookDbModel
import dev.yacsa.repository.model.BookRepoModel
import org.mapstruct.Mapper
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT,
)
interface NewBookRepoDbMapper {

    fun toRepo(
        bookDbModel: BookDbModel
    ): BookRepoModel

    fun toDb(
        bookRepoModel: BookRepoModel
    ): BookDbModel

}