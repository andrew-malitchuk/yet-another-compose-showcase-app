package dev.yacsa.books.featureflag

enum class BooksFlags(val key: String) {
    BOOKS("isBooksFeatureEnabled"),
    FOO("isFooEnabledDebug")
}