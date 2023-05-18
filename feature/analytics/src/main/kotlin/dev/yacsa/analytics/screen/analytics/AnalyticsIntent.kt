package dev.yacsa.analytics.screen.analytics

sealed class AnalyticsIntent {
    object GetList : AnalyticsIntent()
    object Delete : AnalyticsIntent()
}
