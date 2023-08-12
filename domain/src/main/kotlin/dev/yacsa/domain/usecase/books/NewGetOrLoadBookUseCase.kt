package dev.yacsa.domain.usecase.books

import arrow.core.Either
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.BookDomainModel

interface NewGetOrLoadBookUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(bookId: Int): Either<DomainError, BookDomainModel>
}
