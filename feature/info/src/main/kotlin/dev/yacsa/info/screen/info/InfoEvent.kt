package dev.yacsa.info.screen.info

sealed class InfoEvent {
    data class OnInfoClick(val key: String) : InfoEvent()
}
