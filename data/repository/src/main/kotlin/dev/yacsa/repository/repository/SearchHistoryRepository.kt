package dev.yacsa.repository.repository

import dev.yacsa.repository.model.SearchHistoryRepoModel

interface SearchHistoryRepository {
    suspend fun getTop(count: Int): List<SearchHistoryRepoModel>
    suspend fun insert(value: SearchHistoryRepoModel)
    suspend fun deleteAll()
}
