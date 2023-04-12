package dev.yacsa.featureflagmanager.screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.yacsa.featureflagmanager.screen.featureflagmanager.FeatureFlagUiState

@Module
@InstallIn(ViewModelComponent::class)
object FeatureFlagViewModelModule {

    @Provides
    fun providesListUiState(): FeatureFlagUiState = FeatureFlagUiState()
}
