package dev.yacsa.domain.usecase

interface RemoveAllBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke()
}
