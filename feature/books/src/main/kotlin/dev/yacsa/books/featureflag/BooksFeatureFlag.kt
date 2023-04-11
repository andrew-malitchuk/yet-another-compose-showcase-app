package dev.yacsa.books.featureflag

import dev.yacsa.featureflag.FeatureFlag

abstract class BooksFeatureFlag : FeatureFlag() {
    abstract suspend fun isFoo(): Boolean
    abstract override suspend fun isFeatureEnabled(): Boolean
}