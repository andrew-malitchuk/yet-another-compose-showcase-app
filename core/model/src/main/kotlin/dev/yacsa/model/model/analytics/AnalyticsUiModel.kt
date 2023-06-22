package dev.yacsa.model.model.analytics

import android.os.Parcelable
import dev.yacsa.model.model.base.BaseUiModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class AnalyticsUiModel(
    val key: String?,
    var value: String?,
) : BaseUiModel, Parcelable
