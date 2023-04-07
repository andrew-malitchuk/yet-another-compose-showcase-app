package dev.yacsa.network.source

import dev.yacsa.network.model.ResultNetModel

interface BooksNetSource {
    suspend fun getBooks(page:Int): ResultNetModel?
}