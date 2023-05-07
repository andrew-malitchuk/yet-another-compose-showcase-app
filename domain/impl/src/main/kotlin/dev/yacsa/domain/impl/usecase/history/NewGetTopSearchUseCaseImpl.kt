package dev.yacsa.domain.impl.usecase.history

import arrow.core.Either
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.impl.mapper.SearchHistoryDomainRepoMapper
import dev.yacsa.domain.model.SearchHistoryDomainModel
import dev.yacsa.domain.usecase.history.NewGetTopSearchUseCase
import dev.yacsa.repository.repository.SearchHistoryRepository
import javax.inject.Inject


class NewGetTopSearchUseCaseImpl @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
    private val searchHistoryDomainRepoMapper: SearchHistoryDomainRepoMapper,
) : NewGetTopSearchUseCase {

    override suspend fun invoke(): Either<DomainError, List<SearchHistoryDomainModel>> {
        return try{
           val result = searchHistoryRepository.getTop(TOP).map(searchHistoryDomainRepoMapper::toDomain)
            Either.Right(result)
        }catch (ex: Exception) {
            Either.Left(DataError(ex))
        }
    }

    companion object {
        const val TOP = 10
    }
}
