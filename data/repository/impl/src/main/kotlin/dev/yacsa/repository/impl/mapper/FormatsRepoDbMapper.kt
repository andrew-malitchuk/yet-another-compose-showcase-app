package dev.yacsa.repository.impl.mapper

import dev.yacsa.database.model.FormatsDbModel
import dev.yacsa.repository.impl.mapper.base.RepoDbMapper
import dev.yacsa.repository.model.FormatsRepoModel
import javax.inject.Inject

class FormatsRepoDbMapper @Inject constructor() :
    RepoDbMapper<FormatsRepoModel, FormatsDbModel>() {

    override fun toRepo(value: FormatsDbModel): FormatsRepoModel {
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

    override fun toDb(value: FormatsRepoModel): FormatsDbModel {
        return FormatsDbModel(
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
