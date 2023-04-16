package dev.yacsa.featureflag.impl.controller

import dev.yacsa.books.featureflag.BooksFeatureFlag
import dev.yacsa.books.featureflag.IsBooksFeatureEnabled
import dev.yacsa.domain.usecase.featureflag.FetchFeatureFlagUseCase
import dev.yacsa.repository.FeatureFlagRepository
import javax.inject.Inject

class BooksFeatureFlagImpl @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository,
    private val fetchFeatureFlagUseCase: FetchFeatureFlagUseCase,
) : BooksFeatureFlag() {

    override suspend fun isFoo(): Boolean {
//        val key = IsFooEnabledDebug.key ?: return false
//        val localValue = featureFlagRepository.loadFeatureFlag(key).getOrNull()
//        return if (localValue?.value == null) {
//            featureFlagRepository.getFeatureFlagValue(key, key)
//                .getOrElse { false }
//        } else {
//            localValue.value!!
//        }
//        val key = IsFooEnabledDebug.key
//        return fetchFeatureFlagUseCase(key)
        return false
    }

    override suspend fun isFeatureEnabled(): Boolean {
//        val key = IsBooksFeatureEnabled.key ?: return false
//        return featureFlagRepository.getFeatureFlagValue(key, key)
//            .getOrElse {
//                true
//            }

        val key = IsBooksFeatureEnabled.key
        return fetchFeatureFlagUseCase(key)
    }

}