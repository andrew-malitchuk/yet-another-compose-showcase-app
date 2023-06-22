package dev.yacsa.repository.impl.impl

import dev.yacsa.database.source.AnalyticsDbSource
import dev.yacsa.repository.impl.mapper.analytics.AnalyticsRepoDbMapper
import dev.yacsa.repository.model.analytics.AnalyticsRepoModel
import dev.yacsa.repository.repository.AnalyticsRepository
import javax.inject.Inject

class AnalyticsRepositoryImpl @Inject constructor(
    private val analyticsDbSource: AnalyticsDbSource,
    private val analyticsRepoDbMapper: AnalyticsRepoDbMapper,
) : AnalyticsRepository {

    override suspend fun get(): List<AnalyticsRepoModel> {
        return analyticsDbSource.get()?.map(analyticsRepoDbMapper::toRepo) ?: emptyList()
    }

    override suspend fun insert(value: AnalyticsRepoModel) {
        analyticsDbSource.insert(analyticsRepoDbMapper.toDb(value))
    }

    override suspend fun deleteAll() {
        analyticsDbSource.deleteAll()
    }
}
