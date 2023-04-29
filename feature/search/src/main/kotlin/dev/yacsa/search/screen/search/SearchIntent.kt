package dev.yacsa.search.screen.search

sealed class SearchIntent {
    object GetTopSearch : SearchIntent()
    data class Search(val query:String):SearchIntent()
}
