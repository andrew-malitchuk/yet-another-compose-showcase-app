package dev.yacsa.favourite.screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.yacsa.favourite.screen.favourite.FavouriteUiState

@Module
@InstallIn(ViewModelComponent::class)
object FavouriteViewModelModule {

    @Provides
    fun providesFavouriteUiState(): FavouriteUiState = FavouriteUiState()
}
