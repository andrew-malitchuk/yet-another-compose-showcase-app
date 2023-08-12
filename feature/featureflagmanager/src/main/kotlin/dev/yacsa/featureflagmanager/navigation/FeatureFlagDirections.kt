package dev.yacsa.featureflagmanager.navigation

sealed class FeatureFlagDirections(var route: String) {
    object List : FeatureFlagDirections("ff_list")
}
