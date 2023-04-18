package dev.yacsa.domain.usecase.featureflag

interface FetchFeatureFlagUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(key: String): Boolean
}
