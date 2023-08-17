package dev.yacsa.domain.usecase.featureflag

import arrow.core.Either
import dev.yacsa.domain.error.DomainError

interface NewFetchFeatureFlagUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(key: String): Either<DomainError, Boolean>
}
