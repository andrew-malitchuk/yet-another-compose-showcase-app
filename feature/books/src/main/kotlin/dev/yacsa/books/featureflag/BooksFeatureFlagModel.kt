package dev.yacsa.books.featureflag

import dev.yacsa.featureflag.FeatureFlagModel
import kotlinx.parcelize.Parcelize

@Parcelize
object IsBooksFeatureEnabled : FeatureFlagModel("isBooksFeatureEnabled")
object IsGridViewEnabledDebug : FeatureFlagModel("isFooEnabledDebug")

val booksFeatureFlagsList = listOf(
    IsBooksFeatureEnabled,
    IsGridViewEnabledDebug,
)
