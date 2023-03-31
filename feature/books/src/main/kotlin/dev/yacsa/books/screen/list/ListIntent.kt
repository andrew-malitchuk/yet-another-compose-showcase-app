package dev.yacsa.books.screen.list

sealed class ListIntent {
    object GetBooks : ListIntent()
    object RefreshBooks : ListIntent()
    data class BookClicked(val bookId: Int) : ListIntent()
}
