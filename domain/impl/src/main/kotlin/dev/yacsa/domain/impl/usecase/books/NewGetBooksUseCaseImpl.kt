package dev.yacsa.domain.impl.usecase.books

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.NewBookDomainRepoMapper
import dev.yacsa.domain.model.BookDomainModel
import dev.yacsa.domain.usecase.books.NewGetBooksUseCase
import dev.yacsa.repository.repository.BooksRepository
import javax.inject.Inject

class NewGetBooksUseCaseImpl @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookDomainRepoMapper: NewBookDomainRepoMapper,
) : NewGetBooksUseCase {
    @Throws(Throwable::class)
    override suspend fun invoke(page: Int): Either<DomainError, List<BookDomainModel>> {
        return try {
            // туть
            val result = booksRepository.getBookByBage(page).map(bookDomainRepoMapper::toDomain)
            Either.Right(result)
        } catch (ex: Exception) {
            Either.Left(DataError(ex))
        }
    }
}
