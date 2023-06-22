package dev.yacsa.crashlytics.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.crashlytics.impl.provider.CrashlyticsProviderImpl
import dev.yacsa.crashlytics.impl.provider.LoggerProviderImpl
import dev.yacsa.crashlytics.provider.CrashlyticsProvider
import dev.yacsa.crashlytics.provider.LoggerProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ProviderModule {

    @Singleton
    @Binds
    abstract fun bindsCrashlyticsProvider(
        crashlyticsProviderImpl: CrashlyticsProviderImpl,
    ): CrashlyticsProvider

    @Singleton
    @Binds
    abstract fun bindsLoggerProvider(
        loggerProviderImpl: LoggerProviderImpl,
    ): LoggerProvider
}
