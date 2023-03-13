package dev.yacsa.onboarding.navigation

sealed class OnboardingDirection(var route: String) {
    object Onboarding : OnboardingDirection("onboarding_onboarding")
}