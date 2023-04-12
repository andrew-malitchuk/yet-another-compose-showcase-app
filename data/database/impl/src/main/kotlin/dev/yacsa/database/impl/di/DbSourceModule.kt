package dev.yacsa.database.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.database.impl.source.BookAuthorRelationshipDbSourceImpl
import dev.yacsa.database.impl.source.BookDbSourceImpl
import dev.yacsa.database.impl.source.FeatureFlagDbSourceImpl
import dev.yacsa.database.impl.source.PersonDbSourceImpl
import dev.yacsa.database.source.BookAuthorRelationshipDbSource
import dev.yacsa.database.source.BookDbSource
import dev.yacsa.database.source.FeatureFlagDbSource
import dev.yacsa.database.source.PersonDbSource

@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
abstract class DbSourceModule {

    @Binds
    abstract fun bindsBookDbSource(
        bookDbSource: BookDbSourceImpl,
    ): BookDbSource

    @Binds
    abstract fun bindsPersonDbSource(
        personDbSourceImpl: PersonDbSourceImpl,
    ): PersonDbSource

    @Binds
    abstract fun bindsBookAuthorRelationshipDbSource(
        bookAuthorRelationshipDbSourceImpl: BookAuthorRelationshipDbSourceImpl,
    ): BookAuthorRelationshipDbSource

    @Binds
    abstract fun bindsFeatureFlagDbSource(
        featureFlagDbSourceImpl: FeatureFlagDbSourceImpl,
    ): FeatureFlagDbSource
}
