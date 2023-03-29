package dev.yacsa.repository.impl

import dev.yacsa.database.model.BookDbModel
import dev.yacsa.database.source.BookDbSource
import dev.yacsa.network.model.BookNetModel
import dev.yacsa.network.source.BooksNetSource
import dev.yacsa.repository.BooksRepository
import dev.yacsa.repository.impl.mapper.BookRepoDbMapper
import dev.yacsa.repository.impl.mapper.BookRepoNetMapper
import dev.yacsa.repository.model.BookRepoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val bookDbSource: BookDbSource,
    private val booksNetSource: BooksNetSource,
    private val bookRepoNetMapper: BookRepoNetMapper,
    private val bookRepoDbMapper: BookRepoDbMapper
) : BooksRepository {

    override suspend fun getBooks(): List<BookRepoModel> {
        val result = booksNetSource.getBooks(1)?.results ?: emptyList()
        return result.filterNotNull().map(bookRepoNetMapper::toRepo)
    }

    override suspend fun loadBooks(): Flow<List<BookRepoModel>> {
        return bookDbSource
            .getFlow()
            .filterNotNull()
            .map { list ->
                list.map(bookRepoDbMapper::toRepo)
            }
            .onEach { list ->
                if (list.isEmpty()) {
                    refreshBooks()
                }
            }
    }

    override suspend fun saveBooks(values: List<BookRepoModel>) {
        bookDbSource
            .insert(
                values
                    .map(bookRepoDbMapper::toDb)
            )
    }

    override suspend fun refreshBooks() {
        val result = booksNetSource
            .getBooks(1)?.results ?: emptyList<BookNetModel?>()


        result.filterNotNull()
            .map{
                bookRepoNetMapper.toRepo(it)
            }
            .also{
                saveBooks(it)
            }
    }

}