package dev.yacsa.featureflag

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class FeatureFlagModel(val key: String, var value: Boolean? = null) : Parcelable
