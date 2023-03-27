package dev.yacsa.domain.model

import dev.yacsa.domain.model.base.BaseDomainModel

data class StartUpConfigureDomainModel(
    val hasBeenOnboardingShown: Boolean = false
) : BaseDomainModel