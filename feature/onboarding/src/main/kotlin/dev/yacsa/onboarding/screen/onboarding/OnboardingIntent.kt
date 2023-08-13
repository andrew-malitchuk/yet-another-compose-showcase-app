package dev.yacsa.onboarding.screen.onboarding

sealed class OnboardingIntent {
    object GetStatus : OnboardingIntent()
}
