package dev.yacsa.domain.usecase.history

interface ClearHistoryUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke()
}
