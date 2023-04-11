package dev.yacsa.repository.impl

import dev.yacsa.remoteconfig.source.RemoteConfigSource
import dev.yacsa.repository.FeatureFlagRepository
import javax.inject.Inject

class FeatureFlagRepositoryImpl @Inject constructor(
    private val remoteConfigSource: RemoteConfigSource
): FeatureFlagRepository {
    override suspend fun getFeatureFlagValue(key: String, debugKey: String): Result<Boolean> {
        return remoteConfigSource.getBoolean(key)
    }
}