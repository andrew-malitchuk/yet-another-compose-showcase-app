package dev.yacsa.domain.usecase.books

import arrow.core.Either
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.BookDomainModel
import kotlinx.coroutines.flow.Flow

interface SubscribeToFavourite {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Either<DomainError, Flow<List<BookDomainModel?>?>>
}
