package dev.yacsa.domain.usecase

import dev.yacsa.domain.model.BookDomainModel

interface SaveBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(list: List<BookDomainModel>)
}
