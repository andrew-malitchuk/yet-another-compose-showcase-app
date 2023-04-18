package dev.yacsa.books.featureflag

import dev.yacsa.featureflag.FeatureFlag

abstract class BooksFeatureFlag : FeatureFlag() {
    abstract suspend fun isGridViewEnabled(): Boolean
    abstract override suspend fun isFeatureEnabled(): Boolean
}