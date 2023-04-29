package dev.yacsa.domain.usecase.books

import dev.yacsa.domain.model.BookDomainModel

interface SearchBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(query: String): List<BookDomainModel>
}
