package dev.yacsa.featureflag.impl.controller

import dev.yacsa.books.featureflag.BooksFeatureFlag
import dev.yacsa.books.featureflag.IsBooksFeatureEnabled
import dev.yacsa.domain.usecase.featureflag.FetchFeatureFlagUseCase
import javax.inject.Inject

class BooksFeatureFlagImpl @Inject constructor(
    private val fetchFeatureFlagUseCase: FetchFeatureFlagUseCase,
) : BooksFeatureFlag() {

    override suspend fun isGridViewEnabled(): Boolean {
        val key = IsBooksFeatureEnabled.key
        return fetchFeatureFlagUseCase(key)
    }

    override suspend fun isFeatureEnabled(): Boolean {
        val key = IsBooksFeatureEnabled.key
        return fetchFeatureFlagUseCase(key)
    }
}
