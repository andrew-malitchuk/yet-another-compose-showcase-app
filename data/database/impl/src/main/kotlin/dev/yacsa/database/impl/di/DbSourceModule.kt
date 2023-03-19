package dev.yacsa.database.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.database.impl.source.BookDbSourceImpl
import dev.yacsa.database.source.BookDbSource

@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
abstract class DbSourceModule {

    @Binds
    abstract fun bindsBookDbSource(
        bookDbSource: BookDbSourceImpl
    ): BookDbSource

}