package dev.yacsa.analytics.screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.yacsa.analytics.screen.analytics.AnalyticsUiState

@Module
@InstallIn(ViewModelComponent::class)
object AnalyticsViewModelModule {

    @Provides
    fun providesAnalyticsUiState(): AnalyticsUiState = AnalyticsUiState()
}
