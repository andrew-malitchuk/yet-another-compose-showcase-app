package dev.yacsa.domain.usecase.featureflag


interface UpdateLocalFeatureFlagUseCase {
    @Throws(Throwable::class)
    suspend operator fun invoke(key:String, value:Boolean?)
}