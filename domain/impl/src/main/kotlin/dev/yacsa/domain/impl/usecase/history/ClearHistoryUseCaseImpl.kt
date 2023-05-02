package dev.yacsa.domain.impl.usecase.history

import dev.yacsa.domain.usecase.history.ClearHistoryUseCase
import dev.yacsa.repository.repository.SearchHistoryRepository
import javax.inject.Inject

class ClearHistoryUseCaseImpl @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
) : ClearHistoryUseCase {

    override suspend fun invoke() {
        return searchHistoryRepository.deleteAll()
    }
}
