package dev.yacsa.analytics.navigation

sealed class AnalyticsDirection(var route: String) {
    object Analytics : AnalyticsDirection("analytics_list")
}
