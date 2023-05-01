package dev.yacsa.model.model

import android.os.Parcelable
import dev.yacsa.model.model.base.BaseUiModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchHistoryUiModel(
    val query: String?,
) : BaseUiModel, Parcelable