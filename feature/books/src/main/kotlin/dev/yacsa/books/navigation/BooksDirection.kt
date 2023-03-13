package dev.yacsa.books.navigation


sealed class BooksDirection(var route: String) {
    object List : BooksDirection("books_list")
    object Settings : BooksDirection("books_settings")
    object About : BooksDirection("books_about")
}