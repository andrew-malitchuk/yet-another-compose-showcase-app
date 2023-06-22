package dev.yacsa.database.impl.source

import dev.yacsa.database.impl.dao.AnalyticsDbDao
import dev.yacsa.database.model.analytics.AnalyticsDbModel
import dev.yacsa.database.source.AnalyticsDbSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnalyticsDbSourceImpl @Inject constructor(
    private val analyticsDbDao: AnalyticsDbDao,
) : AnalyticsDbSource {

    override suspend fun get(id: Int): AnalyticsDbModel? {
        return analyticsDbDao.get(id)
    }

    override suspend fun get(): List<AnalyticsDbModel>? {
        return analyticsDbDao.get()
    }

    override suspend fun getFlow(): Flow<List<AnalyticsDbModel>?> {
        return analyticsDbDao.subscribe()
    }

    override suspend fun insert(value: AnalyticsDbModel): Int {
        return analyticsDbDao.insert(value).toInt()
    }

    override suspend fun insert(values: List<AnalyticsDbModel>) {
        values.forEach {
            analyticsDbDao.insert(it)
        }
    }

    override suspend fun update(value: AnalyticsDbModel) {
        analyticsDbDao.update(value)
    }

    override suspend fun update(values: List<AnalyticsDbModel>) {
        values.forEach {
            analyticsDbDao.update(it)
        }
    }

    override suspend fun delete(id: Int) {
        analyticsDbDao.delete(id)
    }

    override suspend fun delete(ids: List<Int>) {
        ids.forEach {
            analyticsDbDao.delete(it)
        }
    }

    override suspend fun deleteAll() {
        analyticsDbDao.deleteAll()
    }
}
