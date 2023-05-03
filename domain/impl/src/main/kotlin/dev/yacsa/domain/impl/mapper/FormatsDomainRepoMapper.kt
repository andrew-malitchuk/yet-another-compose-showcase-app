package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.impl.mapper.base.DomainRepoMapper
import dev.yacsa.domain.model.FormatsDomainModel
import dev.yacsa.repository.model.FormatsRepoModel
import javax.inject.Inject

class FormatsDomainRepoMapper @Inject constructor() :
    DomainRepoMapper<FormatsDomainModel, FormatsRepoModel>() {

    override fun toDomain(value: FormatsRepoModel): FormatsDomainModel {
        return FormatsDomainModel(
            value.applicationEpubZip?:"",
            value.applicationOctetStream?:"",
            value.applicationRdfXml?:"",
            value.applicationxMobipocketEbook?:"",
            value.imageJpeg?:"",
            value.textHtml?:"",
            value.textPlain?:"",
            value.textplainCharsetusAscii?:"",
        )
    }

    override fun toRepo(value: FormatsDomainModel): FormatsRepoModel {
        return FormatsRepoModel(
            value.applicationEpubZip,
            value.applicationOctetStream,
            value.applicationRdfXml,
            value.applicationxMobipocketEbook,
            value.imageJpeg,
            value.textHtml,
            value.textPlain,
            value.textplainCharsetusAscii,
        )
    }
}
