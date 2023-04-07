package dev.yacsa.repository.impl

import dev.yacsa.database.model.relationships.BookAuthorRelationship
import dev.yacsa.database.source.BookAuthorRelationshipDbSource
import dev.yacsa.database.source.BookDbSource
import dev.yacsa.database.source.PersonDbSource
import dev.yacsa.network.model.BookNetModel
import dev.yacsa.network.source.BooksNetSource
import dev.yacsa.repository.BooksRepository
import dev.yacsa.repository.impl.mapper.book.BookAuthorRepoDbMapper
import dev.yacsa.repository.impl.mapper.book.BookRepoDbMapper
import dev.yacsa.repository.impl.mapper.book.BookRepoNetMapper
import dev.yacsa.repository.impl.mapper.person.PersonRepoDbMapper
import dev.yacsa.repository.impl.mapper.person.PersonRepoNetMapper
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
    private val bookRepoDbMapper: BookRepoDbMapper,
    private val personDbSource: PersonDbSource,
    private val personRepoNetMapper: PersonRepoNetMapper,
    private val personRepoDbMapper: PersonRepoDbMapper,
    private val bookAuthorRelationshipDbSource: BookAuthorRelationshipDbSource,
    private val bookAuthorRepoDbMapper: BookAuthorRepoDbMapper,
) : BooksRepository {

    override suspend fun getBooks(): List<BookRepoModel> {
        val result = booksNetSource.getBooks(1)?.results ?: emptyList()
        return result.filterNotNull().map(bookRepoNetMapper::toRepo)
    }

    override suspend fun getBook(page: Int): List<BookRepoModel> {
        val result = booksNetSource.getBooks(page)?.results ?: emptyList()
        return result.filterNotNull().map(bookRepoNetMapper::toRepo)
    }

    override suspend fun loadBooks(): Flow<List<BookRepoModel>> {
        return bookAuthorRelationshipDbSource
            .getFlow()
            .filterNotNull()
            .map { list ->
                list.map(bookAuthorRepoDbMapper::toRepo)
            }
            .onEach { list ->
                if (list.isEmpty()) {
                    refreshBooks()
                }
            }
    }

    override suspend fun saveBooks(values: List<BookRepoModel>) {
//        values.forEach {
//            saveBook(it)
//        }

        bookDbSource.insert(values.map(bookRepoDbMapper::toDb))


    }

    override suspend fun saveBook(value: BookRepoModel) {

        val bookDbModel = bookRepoDbMapper.toDb(value)
        val personDbList = value.authors?.filterNotNull()?.map {
            personRepoDbMapper.toDb(it)
        }

        val personDbIds = arrayListOf<Int>()

        personDbList?.let { list ->
            list.forEach {
                val id = personDbSource.insert(it)
                personDbIds.add(id)
            }
//            personDbSource.insert(it)
        }

        bookDbSource
            .insert(
                bookDbModel
            )

        val bookAuthorRelationshipList = arrayListOf<BookAuthorRelationship>()

        personDbIds.forEach {
            bookAuthorRelationshipList.add(
                BookAuthorRelationship(
                    bookDbModel.id,
                    it
                )
            )
        }

        bookAuthorRelationshipList.forEach {
            bookAuthorRelationshipDbSource.insert(
                it
            )
        }
    }

    override suspend fun refreshBooks() {
        val result = booksNetSource
            .getBooks(1)?.results ?: emptyList<BookNetModel?>()


        result.filterNotNull()
            .map {
                bookRepoNetMapper.toRepo(it)
            }
            .also {
                saveBooks(it)
            }
    }

    override suspend fun refreshBooks(page: Int) {
        val result = booksNetSource
            .getBooks(page)?.results ?: emptyList<BookNetModel?>()


        result.filterNotNull()
            .map {
                bookRepoNetMapper.toRepo(it)
            }
            .also {
                saveBooks(it)
            }
    }

    override suspend fun removeAll() {
        bookDbSource.deleteAll()
    }

    //
    override suspend fun getBooksPaged(page: Int): List<BookRepoModel> {
        return bookDbSource.getPaged(page)?.map {
            bookRepoDbMapper.toRepo(it)
        } ?: emptyList()
    }

    override suspend fun savePaged(page: Int, values: List<BookRepoModel>) {

        val db = values.map { bookRepoDbMapper.toDb(it) }
        db.forEach { it.page = page }

        bookDbSource.insert(db)
    }

    override suspend fun removePage(page: Int) {
        bookDbSource.removePage(page)
    }
    //

}