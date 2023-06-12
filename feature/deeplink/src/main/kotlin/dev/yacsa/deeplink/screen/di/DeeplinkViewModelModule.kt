package dev.yacsa.deeplink.screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.yacsa.deeplink.screen.deeplink.DeeplinkUiState

@Module
@InstallIn(ViewModelComponent::class)
object DeeplinkViewModelModule {

    @Provides
    fun providesDeeplinkUiState(): DeeplinkUiState = DeeplinkUiState()
}
