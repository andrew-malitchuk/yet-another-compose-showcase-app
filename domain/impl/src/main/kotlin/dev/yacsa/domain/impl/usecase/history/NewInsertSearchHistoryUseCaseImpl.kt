package dev.yacsa.domain.impl.usecase.history

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.NewSearchHistoryDomainRepoMapper
import dev.yacsa.domain.model.SearchHistoryDomainModel
import dev.yacsa.domain.usecase.history.NewInsertSearchHistoryUseCase
import dev.yacsa.repository.repository.SearchHistoryRepository
import javax.inject.Inject

// TODO: limit count of items
class NewInsertSearchHistoryUseCaseImpl @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
    private val searchHistoryDomainRepoMapper: NewSearchHistoryDomainRepoMapper,
) : NewInsertSearchHistoryUseCase {

    override suspend fun invoke(value: String): Option<DomainError> {
        return try {
            searchHistoryRepository.insert(
                searchHistoryDomainRepoMapper.toRepo(
                    SearchHistoryDomainModel(
                        value,
                    ),
                ),
            )
            none()
        } catch (ex: Exception) {
            DataError(ex).some()
        }
    }
}
