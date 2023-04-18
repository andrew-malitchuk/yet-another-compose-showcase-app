package dev.yacsa.featureflag.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.books.featureflag.BooksFeatureFlag
import dev.yacsa.featureflag.impl.controller.BooksFeatureFlagImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureFlagModule {

    @Binds
    abstract fun bindsBooksFeatureFlag(
        booksFeatureFlagImpl: BooksFeatureFlagImpl,
    ): BooksFeatureFlag
}
