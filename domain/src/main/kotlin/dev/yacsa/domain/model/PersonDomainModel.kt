package dev.yacsa.domain.model

import dev.yacsa.domain.model.base.BaseDomainModel

data class PersonDomainModel(
    val id: Int? = 0,
    val birthYear: Int?,
    val deathYear: Int?,
    val name: String?,
) : BaseDomainModel
