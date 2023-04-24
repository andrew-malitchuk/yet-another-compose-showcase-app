package dev.yacsa.domain.usecase.books

import dev.yacsa.domain.model.BookDomainModel

interface GetOrLoadBookUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(bookId: Int): BookDomainModel?
}
