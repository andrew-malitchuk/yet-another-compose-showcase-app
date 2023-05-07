package dev.yacsa.featureflag.impl.controller

import dev.yacsa.books.featureflag.BooksFeatureFlag
import dev.yacsa.books.featureflag.IsBooksFeatureEnabled
import dev.yacsa.books.featureflag.IsDetalizationEnabled
import dev.yacsa.domain.usecase.featureflag.NewFetchFeatureFlagUseCase
import javax.inject.Inject

class BooksFeatureFlagImpl @Inject constructor(
    private val fetchFeatureFlagUseCase: NewFetchFeatureFlagUseCase,
) : BooksFeatureFlag() {

    override suspend fun isGridViewEnabled(): Boolean {
        val key = IsBooksFeatureEnabled.key
        return fetchFeatureFlagUseCase(key).getOrNull()?:false
    }

    override suspend fun isDetalizationEnabled(): Boolean {
        val key = IsDetalizationEnabled.key
        return fetchFeatureFlagUseCase(key).getOrNull()?:false
    }

    override suspend fun isFeatureEnabled(): Boolean {
        val key = IsBooksFeatureEnabled.key
        return fetchFeatureFlagUseCase(key).getOrNull()?:false
    }
}
