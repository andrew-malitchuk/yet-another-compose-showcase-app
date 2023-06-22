package dev.yacsa.books.screen.detalization

sealed class DetalizationEvent {
    data class OpenWebBrowserWithDetails(val uri: String) : DetalizationEvent()
    data class ShareDeeplink(val uri: String) : DetalizationEvent()
}
