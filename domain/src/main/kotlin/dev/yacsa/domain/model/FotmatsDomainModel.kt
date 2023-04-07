package dev.yacsa.domain.model

import dev.yacsa.domain.model.base.BaseDomainModel

data class FormatsDomainModel(
    val applicationEpubZip: String? = "",
    val applicationOctetStream: String? = "",
    val applicationRdfXml: String? = "",
    val applicationxMobipocketEbook: String? = "",
    val imageJpeg: String? = "",
    val textHtml: String? = "",
    val textPlain: String? = "",
    val textplainCharsetusAscii: String? = ""
) : BaseDomainModel