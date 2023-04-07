package dev.yacsa.domain.usecase.books

import dev.yacsa.domain.model.BookDomainModel

interface SaveBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(page:Int, list: List<BookDomainModel>)
}
