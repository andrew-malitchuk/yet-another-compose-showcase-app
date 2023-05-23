package dev.yacsa.books.screen.detalization

sealed class DetalizationEvent {
    data class OpenWebBrowserWithDetails(val uri: String) : DetalizationEvent()
}
