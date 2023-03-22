package dev.yacsa.domain.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.yacsa.domain.impl.usecase.GetPreferencesUseCaseImpl
import dev.yacsa.domain.impl.usecase.GetStartUpConfigureUseCaseImpl
import dev.yacsa.domain.usecase.GetPreferencesUseCase
import dev.yacsa.domain.usecase.GetStartUpConfigureUseCase
import javax.inject.Singleton

@Suppress("UnnecessaryAbstractClass")
@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Singleton
    @Binds
    abstract fun bindsGetPreferencesUseCase(
        getPreferencesUseCaseImpl: GetPreferencesUseCaseImpl
    ): GetPreferencesUseCase

    @Singleton
    @Binds
    abstract fun bindsGetStartUpConfigureUseCase(
        getStartUpConfigureUseCaseImpl: GetStartUpConfigureUseCaseImpl
    ): GetStartUpConfigureUseCase

}