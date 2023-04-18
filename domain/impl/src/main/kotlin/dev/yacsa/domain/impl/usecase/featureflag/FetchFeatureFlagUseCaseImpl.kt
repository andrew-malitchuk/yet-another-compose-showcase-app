package dev.yacsa.domain.impl.usecase.featureflag

import dev.yacsa.domain.usecase.featureflag.FetchFeatureFlagUseCase
import dev.yacsa.repository.FeatureFlagRepository
import javax.inject.Inject

class FetchFeatureFlagUseCaseImpl @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository,
) : FetchFeatureFlagUseCase {

    override suspend fun invoke(key: String): Boolean {
        val localKeyValue = featureFlagRepository.loadFeatureFlag(key).getOrNull()
        // TODO: fix
        val remoteKeyValue = featureFlagRepository.getFeatureFlagValue(key, key).getOrNull()

        return if (localKeyValue?.value != null) {
            localKeyValue.value ?: remoteKeyValue ?: false
        } else {
            remoteKeyValue ?: false
        }
    }
}
