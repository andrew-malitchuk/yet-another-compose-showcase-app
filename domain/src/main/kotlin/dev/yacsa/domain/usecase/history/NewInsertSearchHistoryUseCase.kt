package dev.yacsa.domain.usecase.history

import arrow.core.Option
import dev.yacsa.domain.error.DomainError

interface NewInsertSearchHistoryUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(value: String): Option<DomainError>
}