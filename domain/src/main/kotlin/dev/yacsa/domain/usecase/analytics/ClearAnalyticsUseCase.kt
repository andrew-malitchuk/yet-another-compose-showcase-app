package dev.yacsa.domain.usecase.analytics

import arrow.core.Option
import dev.yacsa.domain.error.DomainError

interface ClearAnalyticsUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Option<DomainError>
}
