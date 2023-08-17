package dev.yacsa.domain.impl.usecase.featureflag

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.usecase.featureflag.NewUpdateLocalFeatureFlagUseCase
import dev.yacsa.repository.model.FeatureFlagRepoModel
import dev.yacsa.repository.repository.FeatureFlagRepository
import javax.inject.Inject

class NewUpdateLocalFeatureFlagUseCaseImpl @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository,
) : NewUpdateLocalFeatureFlagUseCase {

    override suspend fun invoke(key: String, value: Boolean?): Option<DomainError> {
        return try {
            featureFlagRepository.updateLocalFeatureFlag(
                FeatureFlagRepoModel(
                    key,
                    value,
                ),
            )
            none()
        } catch (ex: Exception) {
            DataError(ex).some()
        }
    }
}
