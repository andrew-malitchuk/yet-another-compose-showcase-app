package dev.yacsa.database.impl.source

import dev.yacsa.database.impl.dao.FeatureFlagDbDao
import dev.yacsa.database.model.FeatureFlagDbModel
import dev.yacsa.database.source.FeatureFlagDbSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FeatureFlagDbSourceImpl @Inject constructor(
    private val featureFlagDbDao: FeatureFlagDbDao
) : FeatureFlagDbSource {

    override suspend fun get(id: Int): FeatureFlagDbModel? {
        return featureFlagDbDao.get(id)
    }

    override suspend fun get(): List<FeatureFlagDbModel>? {
        return featureFlagDbDao.get()
    }

    override suspend fun getFlow(): Flow<List<FeatureFlagDbModel>?> {
        return featureFlagDbDao.getFlow()
    }

    override suspend fun insert(value: FeatureFlagDbModel): Int {
        return featureFlagDbDao.insert(value).toInt()
    }

    override suspend fun insert(values: List<FeatureFlagDbModel>) {
        values.forEach {
            featureFlagDbDao.insert(it)
        }
    }

    override suspend fun update(value: FeatureFlagDbModel) {
//        featureFlagDbDao.update(value)
        featureFlagDbDao.update(value.key,value.value)
    }

    override suspend fun update(values: List<FeatureFlagDbModel>) {
        values.forEach {
            featureFlagDbDao.update(it)
        }
    }

    override suspend fun delete(id: Int) {
        featureFlagDbDao.delete(id)
    }

    override suspend fun delete(ids: List<Int>) {
        ids.forEach {
            featureFlagDbDao.delete(it)
        }
    }

    override suspend fun deleteAll() {
        featureFlagDbDao.deleteAll()
    }

}
