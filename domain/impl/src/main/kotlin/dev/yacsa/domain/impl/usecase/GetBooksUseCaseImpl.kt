package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.impl.mapper.BookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.GetBooksUseCase
import dev.yacsa.repository.BooksRepository
import javax.inject.Inject

class GetBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: BookDomainRepoMapper,
) : GetBooksUseCase {
    override suspend fun invoke(page: Int): List<BookDomainModel> {
//        return booksRepository.getBooks().map(bookDomainRepoMapper::toDomain)
        return booksRepository.getBook(page).map(bookDomainRepoMapper::toDomain)
//        booksRepository.refreshBooks(page)
//        return emptyList()
    }
}