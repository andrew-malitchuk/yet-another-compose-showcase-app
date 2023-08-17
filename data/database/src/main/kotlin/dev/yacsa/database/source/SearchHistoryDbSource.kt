package dev.yacsa.database.source

import dev.yacsa.database.model.SearchHistoryDbModel
import dev.yacsa.database.source.base.BaseDbSource

interface SearchHistoryDbSource : BaseDbSource<SearchHistoryDbModel> {
    suspend fun getTop(count: Int): List<SearchHistoryDbModel>?
}
