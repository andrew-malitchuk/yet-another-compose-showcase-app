package dev.yacsa.domain.usecase.books

import arrow.core.Option
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.BookDomainModel

interface NewSaveBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(page: Int, list: List<BookDomainModel>): Option<DomainError>
}
