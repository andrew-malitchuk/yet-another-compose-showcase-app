package dev.yacsa.domain.usecase.history

interface InsertSearchHistoryUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(value:String)
}
