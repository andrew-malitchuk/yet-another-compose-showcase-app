package dev.yacsa.domain.usecase.secutiry

import arrow.core.Either
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.security.SecurityStatusDomainModel

interface CheckSecurityStatusUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Either<DomainError, SecurityStatusDomainModel>
}
