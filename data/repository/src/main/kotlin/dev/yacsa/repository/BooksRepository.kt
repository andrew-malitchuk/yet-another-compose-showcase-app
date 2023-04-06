package dev.yacsa.repository

import dev.yacsa.repository.model.BookRepoModel
import dev.yacsa.repository.model.RemoteKeyRepoModel
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getBooks(): List<BookRepoModel>
    suspend fun getBook(page:Int): List<BookRepoModel>
    suspend fun loadBooks(): Flow<List<BookRepoModel>>
    suspend fun saveBooks(values: List<BookRepoModel>)
    suspend fun saveBook(value: BookRepoModel)
    suspend fun refreshBooks()
    suspend fun removeAll()

    suspend fun refreshBooks(page: Int)

    suspend fun insertAll(remoteKey: List<RemoteKeyRepoModel>)
    suspend fun remoteId(bookId: Long): RemoteKeyRepoModel?
    suspend fun clearRemoteKeys()
}