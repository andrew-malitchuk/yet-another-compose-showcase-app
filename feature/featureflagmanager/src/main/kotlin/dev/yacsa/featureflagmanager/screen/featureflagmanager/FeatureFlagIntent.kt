package dev.yacsa.featureflagmanager.screen.featureflagmanager


sealed class FeatureFlagIntent {
    object GetFeatureFlags : FeatureFlagIntent()
    data class OnFeatureFlagClick(val bookId: Int) : FeatureFlagIntent()
}
