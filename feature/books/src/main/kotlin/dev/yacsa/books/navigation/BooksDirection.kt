package dev.yacsa.books.navigation

sealed class BooksDirection(var route: String) {
    object List : BooksDirection("books_list")
    object Detalization : BooksDirection("books_detalization/{bookId}") {
        fun getRoute(bookId: Int) = "books_detalization/$bookId"
    }
}
