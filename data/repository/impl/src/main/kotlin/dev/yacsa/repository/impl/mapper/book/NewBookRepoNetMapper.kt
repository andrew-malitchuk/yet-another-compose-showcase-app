package dev.yacsa.repository.impl.mapper.book

import dev.yacsa.network.model.BookNetModel
import dev.yacsa.repository.model.BookRepoModel
import org.mapstruct.Mapper

@Mapper
interface NewBookRepoNetMapper {

    fun toRepo(
        bookNetModel: BookNetModel
    ): BookRepoModel

    fun toNet(
        bookRepoModel: BookRepoModel
    ): BookNetModel

}