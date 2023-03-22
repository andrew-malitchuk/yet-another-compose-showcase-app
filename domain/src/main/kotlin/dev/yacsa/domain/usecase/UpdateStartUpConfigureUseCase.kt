package dev.yacsa.domain.usecase

import dev.yacsa.domain.model.StartUpConfigureDomainModel

interface UpdateStartUpConfigureUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(value: StartUpConfigureDomainModel)
}