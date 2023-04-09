package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.model.PreferencesDomainModel
import dev.yacsa.domain.usecase.GetPreferencesUseCase
import javax.inject.Inject

class GetPreferencesUseCaseImpl @Inject constructor() : GetPreferencesUseCase {

    override suspend fun invoke(): PreferencesDomainModel {
        TODO("Not yet implemented")
    }
}
