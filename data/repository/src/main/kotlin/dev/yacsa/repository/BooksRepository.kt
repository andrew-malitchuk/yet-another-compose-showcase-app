package dev.yacsa.repository

import dev.yacsa.database.model.BookDbModel
import dev.yacsa.repository.model.BookRepoModel
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getBooks(): List<BookRepoModel>
    suspend fun loadBooks(): Flow<List<BookRepoModel>>
    suspend fun saveBooks(values: List<BookRepoModel>)
    suspend fun saveBook(value: BookRepoModel)
    suspend fun refreshBooks()

    suspend fun refreshBooks(page: Int)
}