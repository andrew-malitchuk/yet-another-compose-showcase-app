package dev.yacsa.domain.usecase.books

import arrow.core.Either
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.BookDomainModel

interface NewLoadBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(page: Int): Either<DomainError, List<BookDomainModel>>
}
