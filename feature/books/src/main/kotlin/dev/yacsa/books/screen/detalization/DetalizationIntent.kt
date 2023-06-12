package dev.yacsa.books.screen.detalization

sealed class DetalizationIntent {
    class GetBook(val bookId: Int) : DetalizationIntent()
    class RefreshBook(val bookId: Int) : DetalizationIntent()
    class OnLinkClick(val link: String) : DetalizationIntent()
    class OnShareClick(val id:Int) : DetalizationIntent()
}
