package dev.yacsa.info.screen.info

sealed class InfoIntent {
    object GetInfos : InfoIntent()
    data class OnInfoClick(val key: String) : InfoIntent()
}
