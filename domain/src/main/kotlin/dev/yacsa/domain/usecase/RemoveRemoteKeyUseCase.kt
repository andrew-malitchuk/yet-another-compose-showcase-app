package dev.yacsa.domain.usecase

interface RemoveRemoteKeyUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke()
}