package dev.yacsa.books.screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.yacsa.books.screen.list.ListUiState

@Module
@InstallIn(ViewModelComponent::class)
object BooksViewModelModule {

    @Provides
    fun providesListUiState(): ListUiState = ListUiState()
}
