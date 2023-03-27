package dev.yacsa.domain.usecase

import dev.yacsa.domain.model.PreferencesDomainModel
import dev.yacsa.domain.model.StartUpConfigureDomainModel

// TODO: add kdoc
interface GetStartUpConfigureUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): StartUpConfigureDomainModel?
}