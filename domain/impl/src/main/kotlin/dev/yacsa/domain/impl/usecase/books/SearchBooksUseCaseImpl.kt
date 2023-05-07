package dev.yacsa.domain.impl.usecase.books

import dev.yacsa.domain.impl.mapper.NewBookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.SearchBooksUseCase
import dev.yacsa.repository.repository.BooksRepository
import java.io.IOException
import javax.inject.Inject

class SearchBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: NewBookDomainRepoMapper,
) : SearchBooksUseCase {
    @Throws(Throwable::class)

    override suspend fun invoke(query: String): List<BookDomainModel> {
        val books: List<BookDomainModel> = try {
            val foo = booksRepository.searchOnRemote(query).map(bookDomainRepoMapper::toDomain)
            foo.map {
                it.isCached = false
            }
            foo
        } catch (e: IOException) {
            val foo = booksRepository.searchOnLocal(query).map(bookDomainRepoMapper::toDomain)
            foo.map {
                it.isCached = true
            }
            foo
        }
        return books
    }
}
