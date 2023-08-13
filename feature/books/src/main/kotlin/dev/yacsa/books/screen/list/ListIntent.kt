package dev.yacsa.books.screen.list

sealed class ListIntent {
    object GetBooks : ListIntent()
    object CheckFeatureBlock : ListIntent()
    data class BookClicked(val bookId: Int) : ListIntent()
    object CheckUpdate : ListIntent()
}
