package dev.yacsa.repository

interface FeatureFlagRepository {
    suspend fun getFeatureFlagValue(key: String, debugKey: String): Result<Boolean>
}