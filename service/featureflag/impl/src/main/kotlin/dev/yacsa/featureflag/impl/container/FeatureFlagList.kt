package dev.yacsa.featureflag.impl.container

import dev.yacsa.books.featureflag.booksFeatureFlags
import dev.yacsa.featureflag.BaseFeatureFlagModel

class FeatureFlagList {

    val foo = arrayListOf<BaseFeatureFlagModel>().also {
        it.addAll(booksFeatureFlags)
    }

}