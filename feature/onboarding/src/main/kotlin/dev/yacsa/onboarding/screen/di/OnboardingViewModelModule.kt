package dev.yacsa.onboarding.screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.yacsa.onboarding.screen.onboarding.OnboardingUiState

@Module
@InstallIn(ViewModelComponent::class)
object OnboardingViewModelModule {

    @Provides
    fun providesOnboardingUiState(): OnboardingUiState = OnboardingUiState()
}
