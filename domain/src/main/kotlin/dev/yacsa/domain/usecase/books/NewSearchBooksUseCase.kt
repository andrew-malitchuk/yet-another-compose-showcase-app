package dev.yacsa.domain.usecase.books

import arrow.core.Either
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.BookDomainModel

interface NewSearchBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(
        query: String,
        sort: String? = null,
        lang: String? = null,
    ): Either<DomainError, List<BookDomainModel>>
}
