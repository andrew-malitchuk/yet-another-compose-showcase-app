package dev.yacsa.database.model

import androidx.room.ColumnInfo
import dev.yacsa.database.model.base.BaseDbModel

data class FormatsDbModel(
    @ColumnInfo(name = "application/epub+zip")
    val applicationEpubZip: String?,
    @ColumnInfo(name = "application/octet-stream")
    val applicationOctetStream: String?,
    @ColumnInfo(name = "application/rdf+xml")
    val applicationRdfXml: String?,
    @ColumnInfo(name = "application/x-mobipocket-ebook")
    val applicationxMobipocketEbook: String?,
    @ColumnInfo(name = "image/jpeg")
    val imageJpeg: String?,
    @ColumnInfo(name = "text/html")
    val textHtml: String?,
    @ColumnInfo(name = "text/plain")
    val textPlain: String?,
    @ColumnInfo(name = "text/plain; charset=us-ascii")
    val textplainCharsetusAscii: String?
):BaseDbModel