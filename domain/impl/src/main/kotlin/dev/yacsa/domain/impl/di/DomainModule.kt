package dev.yacsa.domain.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.domain.impl.usecase.*
import dev.yacsa.domain.impl.usecase.analytics.AddAnalyticUseCaseImpl
import dev.yacsa.domain.impl.usecase.analytics.ClearAnalyticsUseCaseImpl
import dev.yacsa.domain.impl.usecase.analytics.GetAnalyticsUseCaseImpl
import dev.yacsa.domain.impl.usecase.books.MarkFavouriteBookImpl
import dev.yacsa.domain.impl.usecase.books.NewGetBooksUseCaseImpl
import dev.yacsa.domain.impl.usecase.books.NewGetOrLoadBookUseCaseImpl
import dev.yacsa.domain.impl.usecase.books.NewLoadBooksUseCaseImpl
import dev.yacsa.domain.impl.usecase.books.NewSaveBooksUseCaseImpl
import dev.yacsa.domain.impl.usecase.books.NewSearchBooksUseCaseImpl
import dev.yacsa.domain.impl.usecase.books.SubscribeToFavouriteImpl
import dev.yacsa.domain.impl.usecase.featureflag.NewFetchFeatureFlagUseCaseImpl
import dev.yacsa.domain.impl.usecase.featureflag.NewUpdateLocalFeatureFlagUseCaseImpl
import dev.yacsa.domain.impl.usecase.history.NewClearHistoryUseCaseImpl
import dev.yacsa.domain.impl.usecase.history.NewGetTopSearchUseCaseImpl
import dev.yacsa.domain.impl.usecase.history.NewInsertSearchHistoryUseCaseImpl
import dev.yacsa.domain.impl.usecase.startupconfigure.NewGetStartUpConfigureUseCaseImpl
import dev.yacsa.domain.impl.usecase.startupconfigure.NewUpdateStartUpConfigureUseCaseImpl
import dev.yacsa.domain.usecase.*
import dev.yacsa.domain.usecase.analytics.AddAnalyticUseCase
import dev.yacsa.domain.usecase.analytics.ClearAnalyticsUseCase
import dev.yacsa.domain.usecase.analytics.GetAnalyticsUseCase
import dev.yacsa.domain.usecase.books.MarkFavouriteBook
import dev.yacsa.domain.usecase.books.NewGetBooksUseCase
import dev.yacsa.domain.usecase.books.NewGetOrLoadBookUseCase
import dev.yacsa.domain.usecase.books.NewLoadBooksUseCase
import dev.yacsa.domain.usecase.books.NewSaveBooksUseCase
import dev.yacsa.domain.usecase.books.NewSearchBooksUseCase
import dev.yacsa.domain.usecase.books.SubscribeToFavourite
import dev.yacsa.domain.usecase.featureflag.NewFetchFeatureFlagUseCase
import dev.yacsa.domain.usecase.featureflag.NewUpdateLocalFeatureFlagUseCase
import dev.yacsa.domain.usecase.history.NewClearHistoryUseCase
import dev.yacsa.domain.usecase.history.NewGetTopSearchUseCase
import dev.yacsa.domain.usecase.history.NewInsertSearchHistoryUseCase
import dev.yacsa.domain.usecase.startupconfigure.NewGetStartUpConfigureUseCase
import dev.yacsa.domain.usecase.startupconfigure.NewUpdateStartUpConfigureUseCase
import javax.inject.Singleton

@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

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

    @Singleton
    @Binds
    abstract fun bindsNewUpdateStartUpConfigureUseCase(
        newUpdateStartUpConfigureUseCase: NewUpdateStartUpConfigureUseCaseImpl,
    ): NewUpdateStartUpConfigureUseCase

    @Singleton
    @Binds
    abstract fun bindsNewGetStartUpConfigureUseCase(
        newGetStartUpConfigureUseCaseImpl: NewGetStartUpConfigureUseCaseImpl,
    ): NewGetStartUpConfigureUseCase

    @Singleton
    @Binds
    abstract fun bindNewFetchFeatureFlagUseCase(
        newFetchFeatureFlagUseCaseImpl: NewFetchFeatureFlagUseCaseImpl,
    ): NewFetchFeatureFlagUseCase

    @Singleton
    @Binds
    abstract fun bindNewUpdateLocalFeatureFlagUseCase(
        newUpdateLocalFeatureFlagUseCaseImpl: NewUpdateLocalFeatureFlagUseCaseImpl,
    ): NewUpdateLocalFeatureFlagUseCase

    @Singleton
    @Binds
    abstract fun bindAddAnalyticUseCase(
        addAnalyticUseCaseImpl: AddAnalyticUseCaseImpl,
    ): AddAnalyticUseCase

    @Singleton
    @Binds
    abstract fun bindClearAnalyticsUseCase(
        clearAnalyticsUseCaseImpl: ClearAnalyticsUseCaseImpl,
    ): ClearAnalyticsUseCase

    @Singleton
    @Binds
    abstract fun bindGetAnalyticsUseCase(
        getAnalyticsUseCaseImpl: GetAnalyticsUseCaseImpl,
    ): GetAnalyticsUseCase

    @Singleton
    @Binds
    abstract fun bindMarkFavouriteBook(
        markFavouriteBookImpl: MarkFavouriteBookImpl,
    ): MarkFavouriteBook

    @Singleton
    @Binds
    abstract fun bindSubscribeToFavourite(
        subscribeToFavouriteImpl: SubscribeToFavouriteImpl,
    ): SubscribeToFavourite
}
