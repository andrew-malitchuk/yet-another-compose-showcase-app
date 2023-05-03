package dev.yacsa.model.mapper

import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.model.FormatsDomainModel
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.model.model.FormatsUiModel
import org.mapstruct.Mapper

@Mapper
interface NewFormatsUiDomainMapper {

    fun toDomain(
        formatsUiModel: FormatsUiModel
    ): FormatsDomainModel

    fun toUi(
        formatsDomainModel: FormatsDomainModel
    ): FormatsUiModel

}