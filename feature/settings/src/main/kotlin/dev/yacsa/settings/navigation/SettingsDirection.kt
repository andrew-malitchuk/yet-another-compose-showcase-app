package dev.yacsa.settings.navigation

sealed class SettingsDirection(var route: String) {
    object Settings : SettingsDirection("settings_settings")
}
