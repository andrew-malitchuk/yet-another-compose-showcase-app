package dev.yacsa.domain.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.domain.impl.usecase.*
import dev.yacsa.domain.impl.usecase.books.GetBooksUseCaseImpl
import dev.yacsa.domain.impl.usecase.books.LoadBooksUseCaseImpl
import dev.yacsa.domain.usecase.*
import dev.yacsa.domain.usecase.books.GetBooksUseCase
import dev.yacsa.domain.usecase.books.LoadBooksUseCase
import dev.yacsa.domain.usecase.books.SaveBooksUseCase
import javax.inject.Singleton

@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun bindsGetPreferencesUseCase(
        getPreferencesUseCaseImpl: GetPreferencesUseCaseImpl,
    ): GetPreferencesUseCase

    @Singleton
    @Binds
    abstract fun bindsGetStartUpConfigureUseCase(
        getStartUpConfigureUseCaseImpl: GetStartUpConfigureUseCaseImpl,
    ): GetStartUpConfigureUseCase

    @Singleton
    @Binds
    abstract fun bindsUpdateStartUpConfigureUseCase(
        updateStartUpConfigureUseCaseImpl: UpdateStartUpConfigureUseCaseImpl,
    ): UpdateStartUpConfigureUseCase

    @Singleton
    @Binds
    abstract fun bindsGetBooksUseCase(
        getBooksUseCaseImpl: GetBooksUseCaseImpl,
    ): GetBooksUseCase

    @Singleton
    @Binds
    abstract fun bindsLoadBooksUseCase(
        loadBooksUseCaseImpl: LoadBooksUseCaseImpl,
    ): LoadBooksUseCase

    @Singleton
    @Binds
    abstract fun bindsRefreshBooksUseCase(
        refreshBooksUseCaseImpl: RefreshBooksUseCaseImpl,
    ): RefreshBooksUseCase

    @Singleton
    @Binds
    abstract fun bindsSaveBooksUseCase(
        saveBooksUseCaseImpl: SaveBooksUseCaseImpl,
    ): SaveBooksUseCase

    @Singleton
    @Binds
    abstract fun bindsRemoveAllBooksUseCase(
        removeAllBooksUseCaseImpl: RemoveAllBooksUseCaseImpl,
    ): RemoveAllBooksUseCase
}
