package dev.yacsa.model.model.update

import android.os.Parcelable
import dev.yacsa.model.model.base.BaseUiModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CheckUpdateUiModel(
    val isSoftUpdate: Boolean,
    val targetVersion: Int,
    val title: String?,
    val content: String?,
) : BaseUiModel, Parcelable