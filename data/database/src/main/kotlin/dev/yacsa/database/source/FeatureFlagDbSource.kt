package dev.yacsa.database.source

import dev.yacsa.database.model.FeatureFlagDbModel
import dev.yacsa.database.source.base.BaseDbSource

interface FeatureFlagDbSource : BaseDbSource<FeatureFlagDbModel>{
    suspend fun getKey(key: String): FeatureFlagDbModel?
}
