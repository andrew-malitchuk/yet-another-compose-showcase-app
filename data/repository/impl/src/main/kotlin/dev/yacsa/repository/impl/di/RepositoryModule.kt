package dev.yacsa.repository.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.repository.impl.impl.AnalyticsRepositoryImpl
import dev.yacsa.repository.impl.impl.BooksRepositoryImpl
import dev.yacsa.repository.impl.impl.CheckUpdateRepositoryImpl
import dev.yacsa.repository.impl.impl.FeatureFlagRepositoryImpl
import dev.yacsa.repository.impl.impl.SearchHistoryRepositoryImpl
import dev.yacsa.repository.impl.impl.StartUpConfigureRepositoryImpl
import dev.yacsa.repository.repository.AnalyticsRepository
import dev.yacsa.repository.repository.BooksRepository
import dev.yacsa.repository.repository.CheckUpdateRepository
import dev.yacsa.repository.repository.FeatureFlagRepository
import dev.yacsa.repository.repository.SearchHistoryRepository
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

    @Binds
    abstract fun bindsSearchHistoryRepository(
        searchHistoryRepositoryImpl: SearchHistoryRepositoryImpl,
    ): SearchHistoryRepository

    @Binds
    abstract fun bindsAnalyticsRepository(
        analyticsRepositoryImpl: AnalyticsRepositoryImpl,
    ): AnalyticsRepository

    @Binds
    abstract fun bindsCheckUpdateRepository(
        checkUpdateRepositoryImpl: CheckUpdateRepositoryImpl,
    ): CheckUpdateRepository
}
