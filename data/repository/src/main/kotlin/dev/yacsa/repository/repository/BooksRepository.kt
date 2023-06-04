package dev.yacsa.repository.repository

import dev.yacsa.repository.model.BookRepoModel
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getBookByBage(page: Int): List<BookRepoModel>
    suspend fun getBook(id: Int): BookRepoModel?
    suspend fun loadBook(id: Int): BookRepoModel?
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
    suspend fun searchOnRemote(
        query: String,
        sort: String?,
        lang: String?
    ): List<BookRepoModel>

    suspend fun searchOnLocal(
        query: String,
        sort: String?,
        lang: String?
    ): List<BookRepoModel>

    suspend fun markFavourite(id:Int, favourite:Boolean)

    suspend fun subscribeFavourite(): Flow<List<BookRepoModel?>?>

}
