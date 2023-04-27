package dev.yacsa.repository.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.repository.impl.impl.BooksRepositoryImpl
import dev.yacsa.repository.impl.impl.FeatureFlagRepositoryImpl
import dev.yacsa.repository.impl.impl.StartUpConfigureRepositoryImpl
import dev.yacsa.repository.repository.BooksRepository
import dev.yacsa.repository.repository.FeatureFlagRepository
import dev.yacsa.repository.repository.StartUpConfigureRepository

@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsBooksRepository(
        booksRepositoryImpl: BooksRepositoryImpl,
    ): BooksRepository

    @Binds
    abstract fun bindsStartUpConfigureRepository(
        startUpConfigureRepositoryImpl: StartUpConfigureRepositoryImpl,
    ): StartUpConfigureRepository

    @Binds
    abstract fun bindsFeatureFlagRepository(
        featureFlagRepositoryImpl: FeatureFlagRepositoryImpl,
    ): FeatureFlagRepository
}
