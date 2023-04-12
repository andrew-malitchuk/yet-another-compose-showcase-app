package dev.yacsa.featureflagmanager.screen.featureflagmanager

sealed class FeatureFlagEvent {
    data class OnFeatureFlagClick(val bookId: Int) : FeatureFlagEvent()
}
