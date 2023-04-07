package dev.yacsa.domain.impl.usecase.books

import dev.yacsa.domain.impl.mapper.BookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.GetBooksUseCase
import dev.yacsa.repository.BooksRepository
import javax.inject.Inject

class GetBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: BookDomainRepoMapper,
) : GetBooksUseCase {
    @Throws(Throwable::class)
    override suspend fun invoke(page: Int): List<BookDomainModel> {
        return booksRepository.getBook(page).map(bookDomainRepoMapper::toDomain)
    }
}