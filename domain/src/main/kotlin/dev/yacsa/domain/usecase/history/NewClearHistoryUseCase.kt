package dev.yacsa.domain.usecase.history

import arrow.core.Option
import dev.yacsa.domain.error.DomainError

interface NewClearHistoryUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Option<DomainError>
}
