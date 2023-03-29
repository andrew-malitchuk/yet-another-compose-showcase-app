package dev.yacsa.domain.usecase

import dev.yacsa.domain.model.BookDomainModel

interface GetBooksUseCase {
    @Throws(Throwable::class)
    suspend fun invoke(): List<BookDomainModel>
}