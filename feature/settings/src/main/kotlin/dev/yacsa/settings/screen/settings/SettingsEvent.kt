package dev.yacsa.settings.screen.settings

sealed class SettingsEvent{
    data class ChangeLang(val lang:String):SettingsEvent()
}
