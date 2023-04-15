package dev.yacsa.featureflag.impl.controller

import dev.yacsa.books.featureflag.BooksFeatureFlag
import dev.yacsa.books.featureflag.IsBooksFeatureEnabled
import dev.yacsa.books.featureflag.IsFooEnabledDebug
import dev.yacsa.repository.FeatureFlagRepository
import javax.inject.Inject

class BooksFeatureFlagImpl @Inject constructor(
    private val featureFlagRepository: FeatureFlagRepository
) : BooksFeatureFlag() {

    override suspend fun isFoo(): Boolean {
        val key = IsFooEnabledDebug.key ?: return false
        val localValue = featureFlagRepository.loadFeatureFlag(key).getOrNull()
        return if (localValue?.value == null) {
            featureFlagRepository.getFeatureFlagValue(key, key)
                .getOrElse { false }
        } else {
            localValue.value!!
        }
    }

    override suspend fun isFeatureEnabled(): Boolean {
        val key = IsBooksFeatureEnabled.key ?: return false
        return featureFlagRepository.getFeatureFlagValue(key, key)
            .getOrElse {
                true
            }
    }

}