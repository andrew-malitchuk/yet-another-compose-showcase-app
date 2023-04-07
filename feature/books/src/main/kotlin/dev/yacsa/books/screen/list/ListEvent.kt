package dev.yacsa.books.screen.list

sealed class ListEvent {
    data class OnBookClick(val bookId:Int) : ListEvent()
}