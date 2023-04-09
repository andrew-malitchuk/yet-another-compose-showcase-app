package dev.yacsa.books.screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.yacsa.books.screen.detalization.DetalizationUiState

@Module
@InstallIn(ViewModelComponent::class)
object DetalizationViewModelModule {

    @Provides
    fun providesDetalizationUiState(): DetalizationUiState = DetalizationUiState()
}
