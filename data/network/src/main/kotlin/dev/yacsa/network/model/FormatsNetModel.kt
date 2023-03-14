package dev.yacsa.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.yacsa.network.model.base.NetModel

/**
 *
 * Expected JSON:
 * ```
 *  {
 *      "text/plain": "https://www.gutenberg.org/ebooks/2641.txt.utf-8",
 *      "application/x-mobipocket-ebook": "https://www.gutenberg.org/ebooks/2641.kf8.images",
 *      "text/html": "https://www.gutenberg.org/ebooks/2641.html.images",
 *      "application/octet-stream": "https://www.gutenberg.org/files/2641/2641-0.zip",
 *      "text/plain; charset=us-ascii": "https://www.gutenberg.org/files/2641/2641-0.txt",
 *      "application/epub+zip": "https://www.gutenberg.org/ebooks/2641.epub3.images",
 *      "image/jpeg": "https://www.gutenberg.org/cache/epub/2641/pg2641.cover.medium.jpg",
 *      "application/rdf+xml": "https://www.gutenberg.org/ebooks/2641.rdf"
 *  }
 * ```
 */
@JsonClass(generateAdapter = true)
data class FormatsNetModel(
    @Json(name = "application/epub+zip")
    val applicationEpubZip: String?,
    @Json(name = "application/octet-stream")
    val applicationOctetStream: String?,
    @Json(name = "application/rdf+xml")
    val applicationRdfXml: String?,
    @Json(name = "application/x-mobipocket-ebook")
    val applicationxMobipocketEbook: String?,
    @Json(name = "image/jpeg")
    val imageJpeg: String?,
    @Json(name = "text/html")
    val textHtml: String?,
    @Json(name = "text/plain")
    val textPlain: String?,
    @Json(name = "text/plain; charset=us-ascii")
    val textplainCharsetusAscii: String?
) : NetModel