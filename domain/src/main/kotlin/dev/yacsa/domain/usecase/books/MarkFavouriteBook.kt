package dev.yacsa.domain.usecase.books

import arrow.core.Option
import dev.yacsa.domain.error.DomainError

interface MarkFavouriteBook {
    @Throws(Throwable::class)
    suspend operator fun invoke(id: Int, isFavourite: Boolean): Option<DomainError>
}
