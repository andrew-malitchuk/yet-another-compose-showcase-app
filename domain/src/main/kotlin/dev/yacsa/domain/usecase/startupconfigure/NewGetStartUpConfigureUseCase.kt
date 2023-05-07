package dev.yacsa.domain.usecase.startupconfigure

import arrow.core.Either
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.model.StartUpConfigureDomainModel


interface NewGetStartUpConfigureUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Either<DomainError, StartUpConfigureDomainModel>
}
