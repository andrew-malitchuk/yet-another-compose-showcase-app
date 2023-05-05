package dev.yacsa.domain.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.domain.impl.usecase.*
import dev.yacsa.domain.impl.usecase.books.NewGetBooksUseCaseImpl
import dev.yacsa.domain.impl.usecase.books.NewGetOrLoadBookUseCaseImpl
import dev.yacsa.domain.impl.usecase.books.NewLoadBooksUseCaseImpl
import dev.yacsa.domain.impl.usecase.books.NewSaveBooksUseCaseImpl
import dev.yacsa.domain.impl.usecase.books.NewSearchBooksUseCaseImpl
import dev.yacsa.domain.impl.usecase.featureflag.FetchFeatureFlagUseCaseImpl
import dev.yacsa.domain.impl.usecase.featureflag.UpdateLocalFeatureFlagUseCaseImpl
import dev.yacsa.domain.impl.usecase.history.NewClearHistoryUseCaseImpl
import dev.yacsa.domain.impl.usecase.history.NewGetTopSearchUseCaseImpl
import dev.yacsa.domain.impl.usecase.history.NewInsertSearchHistoryUseCaseImpl
import dev.yacsa.domain.usecase.*
import dev.yacsa.domain.usecase.books.NewGetBooksUseCase
import dev.yacsa.domain.usecase.books.NewGetOrLoadBookUseCase
import dev.yacsa.domain.usecase.books.NewLoadBooksUseCase
import dev.yacsa.domain.usecase.books.NewSaveBooksUseCase
import dev.yacsa.domain.usecase.books.NewSearchBooksUseCase
import dev.yacsa.domain.usecase.featureflag.FetchFeatureFlagUseCase
import dev.yacsa.domain.usecase.featureflag.UpdateLocalFeatureFlagUseCase
import dev.yacsa.domain.usecase.history.NewClearHistoryUseCase
import dev.yacsa.domain.usecase.history.NewGetTopSearchUseCase
import dev.yacsa.domain.usecase.history.NewInsertSearchHistoryUseCase
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
    abstract fun bindsUpdateLocalFeatureFlagUseCase(
        UpdateLocalFeatureFlagUseCaseImpl: UpdateLocalFeatureFlagUseCaseImpl,
    ): UpdateLocalFeatureFlagUseCase

    @Singleton
    @Binds
    abstract fun bindsFetchFeatureFlagUseCase(
        fetchFeatureFlagUseCaseImpl: FetchFeatureFlagUseCaseImpl,
    ): FetchFeatureFlagUseCase

    @Singleton
    @Binds
    abstract fun bindsNewClearHistoryUseCase(
        newClearHistoryUseCaseImpl: NewClearHistoryUseCaseImpl,
    ): NewClearHistoryUseCase

    @Singleton
    @Binds
    abstract fun bindsNewInsertSearchHistoryUseCase(
        newInsertSearchHistoryUseCaseImpl: NewInsertSearchHistoryUseCaseImpl,
    ): NewInsertSearchHistoryUseCase

    @Singleton
    @Binds
    abstract fun bindsNewGetTopSearchUseCase(
        newGetTopSearchUseCaseImpl: NewGetTopSearchUseCaseImpl,
    ): NewGetTopSearchUseCase

    @Singleton
    @Binds
    abstract fun bindsNewGetBooksUseCase(
        newGetBooksUseCaseImpl: NewGetBooksUseCaseImpl,
    ): NewGetBooksUseCase

    @Singleton
    @Binds
    abstract fun bindsNewSaveBooksUseCase(
        NewSaveBooksUseCaseImpl: NewSaveBooksUseCaseImpl,
    ): NewSaveBooksUseCase

    @Singleton
    @Binds
    abstract fun bindsNewLoadBooksUseCase(
        newLoadBooksUseCaseImpl: NewLoadBooksUseCaseImpl,
    ): NewLoadBooksUseCase

    @Singleton
    @Binds
    abstract fun bindsNewGetOrLoadBookUseCase(
        newGetOrLoadBookUseCaseImpl: NewGetOrLoadBookUseCaseImpl,
    ): NewGetOrLoadBookUseCase

    @Singleton
    @Binds
    abstract fun bindsNewSearchBooksUseCase(
        newSearchBooksUseCaseImpl: NewSearchBooksUseCaseImpl,
    ): NewSearchBooksUseCase
}
