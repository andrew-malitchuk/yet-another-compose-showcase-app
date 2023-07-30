package dev.yacsa.repository.model.update

import com.squareup.moshi.JsonClass
import dev.yacsa.repository.model.base.BaseRepoModel

@JsonClass(generateAdapter = true)
data class CheckUpdateRepoModel(
    val isSoftUpdate: Boolean,
    val targetVersion: Int,
    val title: String?,
    val content: String?,
) : BaseRepoModel
