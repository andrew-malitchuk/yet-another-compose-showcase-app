package dev.yacsa.network.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.network.impl.source.BooksNetSourceImpl
import dev.yacsa.network.source.BooksNetSource

@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
abstract class SourceModule {

    @Binds
    abstract fun bindsBooksNetSource(
        booksNetSourceImpl: BooksNetSourceImpl
    ): BooksNetSource

}