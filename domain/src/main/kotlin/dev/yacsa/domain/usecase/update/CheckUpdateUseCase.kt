package dev.yacsa.domain.usecase.update

import arrow.core.Either
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.update.CheckUpdateDomainModel

interface CheckUpdateUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Either<DomainError, CheckUpdateDomainModel>
}
