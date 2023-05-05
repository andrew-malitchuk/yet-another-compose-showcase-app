package dev.yacsa.domain.impl.usecase.books

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.BookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.NewLoadBooksUseCase
import dev.yacsa.repository.repository.BooksRepository
import javax.inject.Inject

class NewLoadBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: BookDomainRepoMapper,
) : NewLoadBooksUseCase {
    override suspend fun invoke(page: Int): Either<DomainError, List<BookDomainModel>> {
        return try {
            val result = booksRepository.getBooksPaged(page).map(bookDomainRepoMapper::toDomain)
            Either.Right(result)
        } catch (ex: Exception) {
            Either.Left(DataError(ex))
        }
    }
}
