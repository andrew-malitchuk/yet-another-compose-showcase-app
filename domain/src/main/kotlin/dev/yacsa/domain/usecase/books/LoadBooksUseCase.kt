package dev.yacsa.domain.usecase.books

import dev.yacsa.domain.model.BookDomainModel

interface LoadBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(page: Int): List<BookDomainModel>
}
