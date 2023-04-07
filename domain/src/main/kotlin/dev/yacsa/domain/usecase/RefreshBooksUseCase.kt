package dev.yacsa.domain.usecase

import dev.yacsa.domain.model.BookDomainModel
import kotlinx.coroutines.flow.Flow

interface RefreshBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Result<Unit>
}