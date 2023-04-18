package dev.yacsa.repository

import dev.yacsa.repository.model.FeatureFlagRepoModel

interface FeatureFlagRepository {
    suspend fun getFeatureFlagValue(key: String, debugKey: String): Result<Boolean>
    suspend fun loadFeatureFlag(key: String): Result<FeatureFlagRepoModel>
    suspend fun loadFeatureFlags(): Result<List<FeatureFlagRepoModel>>
    suspend fun updateLocalFeatureFlag(featureFlagRepoModel: FeatureFlagRepoModel)
    suspend fun updateKey(key: String)
}
