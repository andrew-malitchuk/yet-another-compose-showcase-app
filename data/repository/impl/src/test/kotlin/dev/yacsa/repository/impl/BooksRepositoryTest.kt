package dev.yacsa.repository.impl

import dev.yacsa.database.source.BookDbSource
import dev.yacsa.network.source.BooksNetSource
import dev.yacsa.repository.model.BookRepoModel
import dev.yacsa.repository.repository.BooksRepository
import kotlinx.coroutines.flow.Flow
import org.mockito.kotlin.mock

class BooksRepositoryTest : BooksRepository {

    private val bookDbSource: BookDbSource = mock<BookDbSource>()
    private val booksNetSource: BooksNetSource = mock<BooksNetSource>()

    override suspend fun getBookByBage(page: Int): List<BookRepoModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getBook(id: Int): BookRepoModel? {
        TODO("Not yet implemented")
    }

    override suspend fun loadBook(id: Int): BookRepoModel? {
        TODO("Not yet implemented")
    }

    override suspend fun saveBooks(values: List<BookRepoModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun saveBook(value: BookRepoModel) {
        TODO("Not yet implemented")
    }

    override suspend fun refreshBooks() {
        TODO("Not yet implemented")
    }

    override suspend fun refreshBooks(page: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun removeAll() {
        TODO("Not yet implemented")
    }

    override suspend fun getBooksPaged(page: Int): List<BookRepoModel> {
        TODO("Not yet implemented")
    }

    override suspend fun savePaged(page: Int, values: List<BookRepoModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun removePage(page: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun searchOnRemote(
        query: String,
        sort: String?,
        lang: String?,
    ): List<BookRepoModel> {
        TODO("Not yet implemented")
    }

    override suspend fun searchOnLocal(
        query: String,
        sort: String?,
        lang: String?,
    ): List<BookRepoModel> {
        TODO("Not yet implemented")
    }

    override suspend fun markFavourite(id: Int, favourite: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun subscribeFavourite(): Flow<List<BookRepoModel?>?> {
        TODO("Not yet implemented")
    }
}
