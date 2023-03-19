package dev.yacsa.domain.usecase

import dev.yacsa.domain.model.PreferencesDomainModel

interface GetPreferencesUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): PreferencesDomainModel
}
