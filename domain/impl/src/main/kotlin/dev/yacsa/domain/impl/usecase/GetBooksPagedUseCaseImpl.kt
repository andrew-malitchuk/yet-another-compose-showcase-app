package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.impl.mapper.BookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.GetBooksPagedUseCase
import dev.yacsa.repository.BooksRepository
import javax.inject.Inject

class GetBooksPagedUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: BookDomainRepoMapper,
) : GetBooksPagedUseCase {
    override suspend fun invoke(page: Int): List<BookDomainModel> {
        return try {
            val fromNet = booksRepository.getBook(page)
            if (!fromNet.isNullOrEmpty()) {
                booksRepository.removePage(page)
                booksRepository.savePaged(page, fromNet)
            }
            fromNet.map(bookDomainRepoMapper::toDomain)

        } catch (e: Exception) {
            val fromDb = booksRepository.getBooksPaged(page)
            fromDb.map(bookDomainRepoMapper::toDomain)

        }
    }
}