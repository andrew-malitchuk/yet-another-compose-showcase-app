package dev.yacsa.domain.usecase

import dev.yacsa.domain.model.BookDomainModel
import kotlinx.coroutines.flow.Flow

interface LoadBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Flow<Result<List<BookDomainModel>>>
}
