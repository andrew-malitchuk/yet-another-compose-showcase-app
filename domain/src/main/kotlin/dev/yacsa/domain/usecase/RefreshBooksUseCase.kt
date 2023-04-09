package dev.yacsa.domain.usecase

interface RefreshBooksUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(): Result<Unit>
}
