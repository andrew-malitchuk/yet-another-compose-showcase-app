package dev.yacsa.repository.repository

import dev.yacsa.repository.model.analytics.AnalyticsRepoModel

interface AnalyticsRepository {
    suspend fun get(): List<AnalyticsRepoModel>
    suspend fun insert(value: AnalyticsRepoModel)
    suspend fun deleteAll()
}
