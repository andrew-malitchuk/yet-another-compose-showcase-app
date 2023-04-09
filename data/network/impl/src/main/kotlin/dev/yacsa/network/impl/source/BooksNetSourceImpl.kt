package dev.yacsa.network.impl.source

import dev.yacsa.network.impl.service.BooksApiService
import dev.yacsa.network.model.ResultNetModel
import dev.yacsa.network.source.BooksNetSource
import javax.inject.Inject

class BooksNetSourceImpl @Inject constructor(
    private val apiService: BooksApiService,
) : BooksNetSource {

    override suspend fun getBooks(page: Int): ResultNetModel? {
        return apiService.getBooks(page)
    }
}
