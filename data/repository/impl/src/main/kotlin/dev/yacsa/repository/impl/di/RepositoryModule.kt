package dev.yacsa.repository.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.repository.BooksRepository
import dev.yacsa.repository.impl.BooksRepositoryImpl

@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsBooksRepository(
        booksRepositoryImpl: BooksRepositoryImpl
    ): BooksRepository

}