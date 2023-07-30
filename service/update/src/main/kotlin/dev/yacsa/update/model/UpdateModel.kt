package dev.yacsa.update.model

data class UpdateModel(
    val isSoftUpdate: Boolean,
    val targetVersion: Int,
    val title: String?,
    val content: String?,
) : BaseUpdateModel
