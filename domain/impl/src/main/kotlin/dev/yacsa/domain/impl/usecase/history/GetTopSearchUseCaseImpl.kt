package dev.yacsa.domain.impl.usecase.history

import dev.yacsa.domain.impl.mapper.SearchHistoryDomainRepoMapper
import dev.yacsa.domain.model.SearchHistoryDomainModel
import dev.yacsa.domain.usecase.history.GetTopSearchUseCase
import dev.yacsa.repository.repository.SearchHistoryRepository
import javax.inject.Inject

class GetTopSearchUseCaseImpl @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
    private val searchHistoryDomainRepoMapper: SearchHistoryDomainRepoMapper
) : GetTopSearchUseCase {

    override suspend fun invoke(): List<SearchHistoryDomainModel> {
        return searchHistoryRepository.getTop(TOP).map(searchHistoryDomainRepoMapper::toDomain)
    }

    companion object {
        const val TOP = 10
    }


}
