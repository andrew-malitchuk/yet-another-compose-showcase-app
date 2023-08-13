package dev.yacsa.model.model

import android.os.Parcelable
import dev.yacsa.model.model.base.BaseUiModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PersonUiModel(
    val id: Int? = 0,
    val birthYear: Int?,
    val deathYear: Int?,
    val name: String?,
) : BaseUiModel, Parcelable
