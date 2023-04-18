package dev.yacsa.domain.impl.usecase.featureflag

import dev.yacsa.domain.usecase.featureflag.UpdateLocalFeatureFlagUseCase
import dev.yacsa.repository.FeatureFlagRepository
import dev.yacsa.repository.model.FeatureFlagRepoModel
import javax.inject.Inject

class UpdateLocalFeatureFlagUseCaseImpl @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository,
) : UpdateLocalFeatureFlagUseCase {

    override suspend fun invoke(key: String, value: Boolean?) {
        featureFlagRepository.updateLocalFeatureFlag(
            FeatureFlagRepoModel(
                key,
                value,
            ),
        )
    }
}
