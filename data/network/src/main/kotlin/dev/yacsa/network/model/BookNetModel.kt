package dev.yacsa.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.yacsa.network.model.base.BaseNetModel

/**
 *
 * Expected JSON:
 * ```
 *  {
 *      "id": 2641,
 *      "title": "A Room with a View",
 *      "authors": [
 *          {
 *              "name": "Forster, E. M. (Edward Morgan)",
 *              "birth_year": 1879,
 *              "death_year": 1970
 *          }
 *      ],
 *      "subjects": [
 *          "British -- Italy -- Fiction",
 *          "England -- Fiction",
 *          "Florence (Italy) -- Fiction",
 *          "Humorous stories",
 *          "Young women -- Fiction"
 *      ],
 *      "bookshelves": [
 *          "Italy"
 *      ],
 *      "languages": [
 *          "en"
 *      ],
 *      "copyright": false,
 *      "media_type": "Text",
 *      "formats": {
 *          "text/plain": "https://www.gutenberg.org/ebooks/2641.txt.utf-8",
 *          "application/x-mobipocket-ebook": "https://www.gutenberg.org/ebooks/2641.kf8.images",
 *          "text/html": "https://www.gutenberg.org/ebooks/2641.html.images",
 *          "application/octet-stream": "https://www.gutenberg.org/files/2641/2641-0.zip",
 *          "text/plain; charset=us-ascii": "https://www.gutenberg.org/files/2641/2641-0.txt",
 *          "application/epub+zip": "https://www.gutenberg.org/ebooks/2641.epub3.images",
 *          "image/jpeg": "https://www.gutenberg.org/cache/epub/2641/pg2641.cover.medium.jpg",
 *          "application/rdf+xml": "https://www.gutenberg.org/ebooks/2641.rdf"
 *      },
 *      "download_count": 167726
 * }
 * ```
 */
@JsonClass(generateAdapter = true)
data class BookNetModel(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "title")
    val title: String?,
    @Json(name = "subjects")
    val subjects: List<String?>?,
    @Json(name = "authors")
    val authors: List<PersonNetModel?>?,
    @Json(name = "bookshelves")
    val bookshelves: List<String?>?,
    @Json(name = "languages")
    val languages: List<String?>?,
    @Json(name = "copyright")
    val copyright: Boolean?,
    @Json(name = "media_type")
    val mediaType: String?,
    @Json(name = "formats")
    val formats: FormatsNetModel?,
    @Json(name = "download_count")
    val downloadCount: Int?,
) : BaseNetModel
