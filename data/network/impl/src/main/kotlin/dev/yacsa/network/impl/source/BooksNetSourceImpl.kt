package dev.yacsa.network.impl.source

import dev.yacsa.network.impl.service.BooksApiService
import dev.yacsa.network.model.BookNetModel
import dev.yacsa.network.model.ResultNetModel
import dev.yacsa.network.source.BooksNetSource
import javax.inject.Inject

class BooksNetSourceImpl @Inject constructor(
    private val apiService: BooksApiService,
) : BooksNetSource {

    override suspend fun getBooks(page: Int): ResultNetModel? {
        return apiService.getBooks(page)
    }

    override suspend fun getBook(bookId: Int): BookNetModel? {
        return apiService.getBook(bookId)
    }

    override suspend fun search(query: String): ResultNetModel? {
        return apiService.search(query)
    }
}
