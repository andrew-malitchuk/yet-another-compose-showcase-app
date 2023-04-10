package dev.yacsa.featureflag.impl.controller

import dev.yacsa.books.featureflag.BooksFeatureFlag
import javax.inject.Inject

class BooksFeatureFlagImpl @Inject constructor() : BooksFeatureFlag() {

    override fun isFoo(): Boolean {
        return true
    }

    override fun isFeatureEnabled(): Boolean {
        return false
    }

}