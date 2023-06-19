package dev.yacsa.repository.model

import dev.yacsa.repository.model.base.BaseRepoModel

data class StartUpConfigureRepoModel(
    val hasBeenOnboardingShown: Boolean,
    var language:String?
) : BaseRepoModel
