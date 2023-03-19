package dev.yacsa.domain.model

import dev.yacsa.domain.model.base.BaseDomainModel

data class PreferencesDomainModel(
    val theme: String
) : BaseDomainModel