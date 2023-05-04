package dev.yacsa.domain.impl.usecase.books

import dev.yacsa.domain.impl.mapper.NewBookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.LoadBooksUseCase
import dev.yacsa.repository.repository.BooksRepository
import javax.inject.Inject

class LoadBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: NewBookDomainRepoMapper,
) : LoadBooksUseCase {
    override suspend fun invoke(page: Int): List<BookDomainModel> {
        return booksRepository.getBooksPaged(page).map(bookDomainRepoMapper::toDomain)
    }
}
