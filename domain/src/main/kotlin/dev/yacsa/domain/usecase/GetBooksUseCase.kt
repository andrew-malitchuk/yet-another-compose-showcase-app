package dev.yacsa.domain.usecase

import dev.yacsa.domain.model.BookDomainModel

interface GetBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(page:Int): List<BookDomainModel>
}