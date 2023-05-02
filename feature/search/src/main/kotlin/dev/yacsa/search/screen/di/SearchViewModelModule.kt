package dev.yacsa.search.screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.yacsa.search.screen.search.SearchUiState

@Module
@InstallIn(ViewModelComponent::class)
object SearchViewModelModule {

    @Provides
    fun providesSearchUiState(): SearchUiState = SearchUiState()
}
