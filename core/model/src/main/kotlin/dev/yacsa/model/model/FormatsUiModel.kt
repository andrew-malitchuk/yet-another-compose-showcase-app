package dev.yacsa.model.model

import android.os.Parcelable
import dev.yacsa.model.model.base.BaseUiModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormatsUiModel(
    val applicationEpubZip: String,
    val applicationOctetStream: String,
    val applicationRdfXml: String,
    val applicationxMobipocketEbook: String,
    val imageJpeg: String,
    val textHtml: String,
    val textPlain: String,
    val textplainCharsetusAscii: String,
) : BaseUiModel, Parcelable
