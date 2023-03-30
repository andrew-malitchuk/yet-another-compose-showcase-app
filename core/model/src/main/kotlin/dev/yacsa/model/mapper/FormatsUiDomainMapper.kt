package dev.yacsa.model.mapper

import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.model.FormatsDomainModel
import dev.yacsa.model.mapper.base.UiDomainMapper
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.model.model.FormatsUiModel
import javax.inject.Inject


class FormatsUiDomainMapper @Inject constructor() :
    UiDomainMapper<FormatsUiModel, FormatsDomainModel>() {
    override fun toUi(value: FormatsDomainModel): FormatsUiModel {
        return FormatsUiModel(
            value.applicationEpubZip,
            value.applicationOctetStream,
            value.applicationRdfXml,
            value.applicationxMobipocketEbook,
            value.imageJpeg,
            value.textHtml,
            value.textPlain,
            value.textplainCharsetusAscii
        )
    }

    override fun toDomain(value: FormatsUiModel): FormatsDomainModel {
        return FormatsDomainModel(
            value.applicationEpubZip,
            value.applicationOctetStream,
            value.applicationRdfXml,
            value.applicationxMobipocketEbook,
            value.imageJpeg,
            value.textHtml,
            value.textPlain,
            value.textplainCharsetusAscii
        )
    }

}
