package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.repository.model.BookRepoModel
import org.mapstruct.Mapper

@Mapper
interface NewBookDomainRepoMapper {

    fun toRepo(
        bookDomainModel: BookDomainModel
    ): BookRepoModel

    fun toDomain(
        bookRepoModel: BookRepoModel
    ): BookDomainModel

}