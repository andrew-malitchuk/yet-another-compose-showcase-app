package dev.yacsa.main.screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.yacsa.main.screen.main.MainUiState

@Module
@InstallIn(ViewModelComponent::class)
object MainViewModelModule {

    @Provides
    fun providesMainUiState(): MainUiState = MainUiState()
}
