package dev.yacsa.repository.impl.mapper

import dev.yacsa.network.model.BookNetModel
import dev.yacsa.network.model.FormatsNetModel
import dev.yacsa.repository.impl.mapper.base.RepoNetMapper
import dev.yacsa.repository.model.BookRepoModel
import dev.yacsa.repository.model.FormatsRepoModel
import javax.inject.Inject

class FormatsRepoNetMapper @Inject constructor() :
    RepoNetMapper<FormatsRepoModel, FormatsNetModel>() {
    override fun toRepo(value: FormatsNetModel): FormatsRepoModel {
        return FormatsRepoModel(
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

    override fun toNet(value: FormatsRepoModel): FormatsNetModel {
        return FormatsNetModel(
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