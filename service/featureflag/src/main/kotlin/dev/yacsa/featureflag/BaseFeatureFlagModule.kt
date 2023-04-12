package dev.yacsa.featureflag

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BaseFeatureFlagModel(
    val key: String
) : Parcelable