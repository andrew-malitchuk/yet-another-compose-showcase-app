package dev.yacsa.featureflagmanager.screen.featureflagmanager

sealed class FeatureFlagEvent {
    data class OnFeatureFlagClick(val key: String) : FeatureFlagEvent()
}
