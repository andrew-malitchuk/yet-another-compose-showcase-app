package dev.yacsa.featureflag

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// TODO: fix
@Parcelize
open class FooFlag(val key: String) : Parcelable