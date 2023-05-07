package dev.yacsa.repository.impl.impl

import dev.yacsa.database.source.SearchHistoryDbSource
import dev.yacsa.repository.impl.mapper.search.NewSearchHistoryRepoDbMapper
import dev.yacsa.repository.model.SearchHistoryRepoModel
import dev.yacsa.repository.repository.SearchHistoryRepository
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor(
    private val searchHistoryDbSource: SearchHistoryDbSource,
    private val searchHistoryRepoDbMapper: NewSearchHistoryRepoDbMapper,
) : SearchHistoryRepository {

    override suspend fun getTop(count: Int): List<SearchHistoryRepoModel> {
        return (
                searchHistoryDbSource.getTop(count)
                    ?: emptyList()
                ).map(searchHistoryRepoDbMapper::toRepo)
    }

    override suspend fun insert(value: SearchHistoryRepoModel) {
        searchHistoryDbSource.insert(searchHistoryRepoDbMapper.toDb(value).also {
            it.createAt = System.currentTimeMillis()
        })
    }

    override suspend fun deleteAll() {
        searchHistoryDbSource.deleteAll()
    }
}
