package dev.yacsa.navigation

// TODO: maybe, rename to `RootDirection` (maybe)
sealed class NavigationDirection(var route: String) {
    object Root : NavigationDirection("root")
    object Onboarding : NavigationDirection("onboarding")
    object Books : NavigationDirection("books")
    object Format : NavigationDirection("format")
    object Person : NavigationDirection("person")
    object FeatureFlag : NavigationDirection("feature_flag")
    object NotFound : NavigationDirection("not_found")
    object Search : NavigationDirection("search")
}
