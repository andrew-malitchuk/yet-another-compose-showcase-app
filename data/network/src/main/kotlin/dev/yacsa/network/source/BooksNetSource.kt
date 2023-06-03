package dev.yacsa.network.source

import dev.yacsa.network.model.BookNetModel
import dev.yacsa.network.model.ResultNetModel

interface BooksNetSource {
    suspend fun getBooks(page: Int): ResultNetModel?
    suspend fun getBook(bookId: Int): BookNetModel?
    suspend fun search(
        query: String,
        sort: String?,
        lang: String?
    ): ResultNetModel?
}
