package dev.yacsa.info.screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.yacsa.info.screen.info.InfoUiState

@Module
@InstallIn(ViewModelComponent::class)
object InfoViewModelModule {

    @Provides
    fun providesListUiState(): InfoUiState = InfoUiState()
}
