package dev.yacsa.repository.impl.mapper

import dev.yacsa.database.model.SearchHistoryDbModel
import dev.yacsa.repository.impl.mapper.base.RepoDbMapper
import dev.yacsa.repository.model.SearchHistoryRepoModel
import javax.inject.Inject

class SearchHistoryRepoDbMapper @Inject constructor() :
    RepoDbMapper<SearchHistoryRepoModel, SearchHistoryDbModel>() {

    override fun toRepo(value: SearchHistoryDbModel): SearchHistoryRepoModel {
        return SearchHistoryRepoModel(value.query)
    }

    override fun toDb(value: SearchHistoryRepoModel): SearchHistoryDbModel {
        return SearchHistoryDbModel(
            0,
            value.query,
            System.currentTimeMillis(),
        )
    }
}
