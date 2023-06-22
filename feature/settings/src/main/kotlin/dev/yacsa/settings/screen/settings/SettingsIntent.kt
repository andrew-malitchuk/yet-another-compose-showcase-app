package dev.yacsa.settings.screen.settings

sealed class SettingsIntent {
    object GetTheme : SettingsIntent()
    object GetLang : SettingsIntent()
    data class SetLang(val lang: String) : SettingsIntent()
}
