package dev.yacsa.domain.impl.usecase.history

import dev.yacsa.domain.impl.mapper.SearchHistoryDomainRepoMapper
import dev.yacsa.domain.model.SearchHistoryDomainModel
import dev.yacsa.domain.usecase.history.InsertSearchHistoryUseCase
import dev.yacsa.repository.repository.SearchHistoryRepository
import javax.inject.Inject

// TODO: limit count of items
class InsertSearchHistoryUseCaseImpl @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
    private val searchHistoryDomainRepoMapper: SearchHistoryDomainRepoMapper
) : InsertSearchHistoryUseCase {

    override suspend fun invoke(value: String) {
        searchHistoryRepository.insert(
            searchHistoryDomainRepoMapper.toRepo(
                SearchHistoryDomainModel(
                    value
                )
            )
        )
    }

}
