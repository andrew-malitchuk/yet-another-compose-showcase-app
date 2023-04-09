package dev.yacsa.repository

import dev.yacsa.repository.model.BookRepoModel
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getBooks(): List<BookRepoModel>
    suspend fun getBook(page: Int): List<BookRepoModel>
    suspend fun loadBooks(): Flow<List<BookRepoModel>>
    suspend fun saveBooks(values: List<BookRepoModel>)
    suspend fun saveBook(value: BookRepoModel)
    suspend fun refreshBooks()
    suspend fun removeAll()

    suspend fun refreshBooks(page: Int)

    //
    suspend fun getBooksPaged(page: Int): List<BookRepoModel>
    suspend fun savePaged(page: Int, values: List<BookRepoModel>)
    suspend fun removePage(page: Int)
    //
}
