package dev.yacsa.domain.usecase.history

import arrow.core.Either
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.SearchHistoryDomainModel

interface NewGetTopSearchUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Either<DomainError, List<SearchHistoryDomainModel>>
}
