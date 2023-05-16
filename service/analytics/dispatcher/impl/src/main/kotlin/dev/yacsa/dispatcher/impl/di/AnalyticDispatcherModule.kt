package dev.yacsa.dispatcher.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.dispatcher.AnalyticDispatcher
import dev.yacsa.dispatcher.impl.AnalyticDispatcherImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnalyticDispatcherModule {

    @Singleton
    @Binds
    abstract fun bindsAnalyticDispatcher(
        analyticDispatcherImpl: AnalyticDispatcherImpl,
    ): AnalyticDispatcher

}