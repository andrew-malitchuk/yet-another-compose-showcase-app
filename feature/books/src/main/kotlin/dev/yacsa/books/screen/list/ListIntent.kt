package dev.yacsa.books.screen.list

sealed class ListIntent {
    object GetBooks : ListIntent()
    data class BookClicked(val bookId: Int) : ListIntent()
}
