package dev.yacsa.repository.model

import dev.yacsa.repository.model.base.BaseRepoModel

data class PersonRepoModel(
    val id: Int? ,
    val birthYear: Int?,
    val deathYear: Int?,
    val name: String?,
) : BaseRepoModel
