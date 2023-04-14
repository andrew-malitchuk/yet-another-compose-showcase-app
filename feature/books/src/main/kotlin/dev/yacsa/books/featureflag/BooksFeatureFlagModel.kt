package dev.yacsa.books.featureflag

import dev.yacsa.featureflag.BaseFeatureFlagModel

//class BooksFeatureFlagModel(key: String) : BaseFeatureFlagModel(key)

val booksFeatureFlags = listOf<BaseFeatureFlagModel>(
    BaseFeatureFlagModel("isBooksFeatureEnabled"),
    BaseFeatureFlagModel("isFooEnabledDebug")
)