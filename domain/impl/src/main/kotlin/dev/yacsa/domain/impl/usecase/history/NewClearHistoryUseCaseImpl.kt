package dev.yacsa.domain.impl.usecase.history

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.DomainError
import dev.yacsa.domain.usecase.history.NewClearHistoryUseCase
import dev.yacsa.repository.repository.SearchHistoryRepository
import javax.inject.Inject

class NewClearHistoryUseCaseImpl @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository,
) : NewClearHistoryUseCase {

    // Raise doesnt work
    // https://slack-chats.kotlinlang.org/t/10974556/have-anyone-experienced-this-error-it-happened-right-after-e
    override suspend fun invoke(): Option<DomainError> {
        return try {
            searchHistoryRepository.deleteAll()
            none()
        } catch (ex: Exception) {
            DataError(ex).some()
        }
    }
}
