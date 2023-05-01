package dev.yacsa.domain.usecase.history

import dev.yacsa.domain.model.SearchHistoryDomainModel

interface GetTopSearchUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): List<SearchHistoryDomainModel>
}
