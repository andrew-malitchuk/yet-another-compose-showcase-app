package dev.yacsa.repository.model

import dev.yacsa.repository.model.base.BaseRepoModel

data class FormatsRepoModel(
    val applicationEpubZip: String?,
    val applicationOctetStream: String?,
    val applicationRdfXml: String?,
    val applicationxMobipocketEbook: String?,
    val imageJpeg: String?,
    val textHtml: String?,
    val textPlain: String?,
    val textplainCharsetusAscii: String?,
) : BaseRepoModel
