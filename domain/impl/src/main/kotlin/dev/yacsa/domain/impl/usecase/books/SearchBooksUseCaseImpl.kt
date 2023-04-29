package dev.yacsa.domain.impl.usecase.books

import dev.yacsa.domain.impl.mapper.BookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.SearchBooksUseCase
import dev.yacsa.repository.repository.BooksRepository
import javax.inject.Inject

class SearchBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: BookDomainRepoMapper,
) : SearchBooksUseCase {
    @Throws(Throwable::class)

    override suspend fun invoke(query: String): List<BookDomainModel> {
        return booksRepository.searchOnRemote(query).map(bookDomainRepoMapper::toDomain)
    }
}
