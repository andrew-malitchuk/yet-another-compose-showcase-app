package dev.yacsa.books.featureflag

import dev.yacsa.featureflag.FooFlag
import kotlinx.parcelize.Parcelize

@Parcelize
object IsBooksFeatureEnabled : FooFlag("isBooksFeatureEnabled")
object IsFooEnabledDebug : FooFlag("isFooEnabledDebug")

val fooList = listOf(
    IsBooksFeatureEnabled,
    IsFooEnabledDebug
)