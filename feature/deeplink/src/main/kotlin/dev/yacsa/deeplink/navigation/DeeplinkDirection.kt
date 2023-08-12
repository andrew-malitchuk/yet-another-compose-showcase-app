package dev.yacsa.deeplink.navigation

sealed class DeeplinkDirection(var route: String) {
    object Deeplink : DeeplinkDirection("deeplink_list")
}
