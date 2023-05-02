package dev.yacsa.database.impl.source

import dev.yacsa.database.impl.dao.SearchHistoryDao
import dev.yacsa.database.model.SearchHistoryDbModel
import dev.yacsa.database.source.SearchHistoryDbSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchHistoryDbSourceImpl @Inject constructor(
    private val searchHistoryDao: SearchHistoryDao,
) : SearchHistoryDbSource {

    override suspend fun get(id: Int): SearchHistoryDbModel? {
        return searchHistoryDao.get(id)
    }

    override suspend fun getTop(count: Int): List<SearchHistoryDbModel>? {
        return searchHistoryDao.getTop(count)
    }

    override suspend fun get(): List<SearchHistoryDbModel>? {
        return searchHistoryDao.get()
    }

    override suspend fun getFlow(): Flow<List<SearchHistoryDbModel>?> {
        return searchHistoryDao.subscribe()
    }

    override suspend fun insert(value: SearchHistoryDbModel): Int {
        return searchHistoryDao.insert(value).toInt()
    }

    override suspend fun insert(values: List<SearchHistoryDbModel>) {
        values.forEach {
            searchHistoryDao.insert(it)
        }
    }

    override suspend fun update(value: SearchHistoryDbModel) {
        searchHistoryDao.update(value)
    }

    override suspend fun update(values: List<SearchHistoryDbModel>) {
        values.forEach {
            searchHistoryDao.update(it)
        }
    }

    override suspend fun delete(id: Int) {
        searchHistoryDao.delete(id)
    }

    override suspend fun delete(ids: List<Int>) {
        ids.forEach {
            searchHistoryDao.delete(it)
        }
    }

    override suspend fun deleteAll() {
        searchHistoryDao.deleteAll()
    }
}
