package dev.yacsa.repository.impl.mapper.book

import dev.yacsa.database.model.BookDbModel
import dev.yacsa.repository.model.BookRepoModel
import org.mapstruct.Mapper

@Mapper
interface NewBookRepoDbMapper {

    fun toRepo(
        bookDbModel: BookDbModel
    ): BookRepoModel

    fun toDb(
        bookRepoModel: BookRepoModel
    ): BookDbModel

}