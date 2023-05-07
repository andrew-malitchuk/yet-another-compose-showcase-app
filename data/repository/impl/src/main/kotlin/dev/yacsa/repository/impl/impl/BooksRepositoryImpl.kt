package dev.yacsa.repository.impl.impl

import dev.yacsa.database.model.relationships.BookAuthorRelationship
import dev.yacsa.database.source.BookAuthorRelationshipDbSource
import dev.yacsa.database.source.BookDbSource
import dev.yacsa.database.source.PersonDbSource
import dev.yacsa.network.model.BookNetModel
import dev.yacsa.network.source.BooksNetSource
import dev.yacsa.repository.impl.mapper.book.NewBookRepoDbMapper
import dev.yacsa.repository.impl.mapper.book.NewBookRepoNetMapper
import dev.yacsa.repository.impl.mapper.person.NewPersonRepoDbMapper
import dev.yacsa.repository.impl.mapper.person.NewPersonRepoNetMapper
import dev.yacsa.repository.model.BookRepoModel
import dev.yacsa.repository.repository.BooksRepository
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val bookDbSource: BookDbSource,
    private val booksNetSource: BooksNetSource,
    private val bookRepoNetMapper: NewBookRepoNetMapper,
    private val bookRepoDbMapper: NewBookRepoDbMapper,
    private val personDbSource: PersonDbSource,
    private val personRepoNetMapper: NewPersonRepoNetMapper,
    private val personRepoDbMapper: NewPersonRepoDbMapper,
    private val bookAuthorRelationshipDbSource: BookAuthorRelationshipDbSource,
) : BooksRepository {


    override suspend fun getBookByBage(page: Int): List<BookRepoModel> {
        val result = booksNetSource.getBooks(page)?.results ?: emptyList()
        return result.filterNotNull().map(bookRepoNetMapper::toRepo)
    }

    override suspend fun getBook(id: Int): BookRepoModel? {
        return booksNetSource.getBook(id)?.let { bookRepoNetMapper.toRepo(it) }
    }

    override suspend fun loadBook(id: Int): BookRepoModel? {
        return bookDbSource.get(id)?.let { bookRepoDbMapper.toRepo(it) }
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
        }

        bookDbSource
            .insert(
                bookDbModel,
            )

        val bookAuthorRelationshipList = arrayListOf<BookAuthorRelationship>()

        personDbIds.forEach {
            bookAuthorRelationshipList.add(
                BookAuthorRelationship(
                    bookDbModel.id,
                    it,
                ),
            )
        }

        bookAuthorRelationshipList.forEach {
            bookAuthorRelationshipDbSource.insert(
                it,
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

    override suspend fun searchOnRemote(query: String): List<BookRepoModel> {
        val result = booksNetSource.search(query)?.results ?: emptyList()
        return result.filterNotNull().map(bookRepoNetMapper::toRepo)
    }

    override suspend fun searchOnLocal(query: String): List<BookRepoModel> {
        val result = bookDbSource.search(query)
        return result.map(bookRepoDbMapper::toRepo)
    }
}
