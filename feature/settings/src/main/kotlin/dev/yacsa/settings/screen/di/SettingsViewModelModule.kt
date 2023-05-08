package dev.yacsa.settings.screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.yacsa.settings.screen.settings.SettingsUiState

@Module
@InstallIn(ViewModelComponent::class)
object SettingsViewModelModule {

    @Provides
    fun providesSettingsUiState(): SettingsUiState = SettingsUiState()
}
