package dev.yacsa.model.mapper

import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.model.FormatsDomainModel
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.model.model.FormatsUiModel
import org.mapstruct.Mapper

@Mapper
interface NewBooksUiDomainMapper {

    fun toDomain(
        bookUiModel: BookUiModel
    ): BookDomainModel

    fun toUi(
        bookDomainModel: BookDomainModel
    ): BookUiModel

}