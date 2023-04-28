package dev.yacsa.search.navigation

sealed class SearchDirection(var route: String) {
    object Search : SearchDirection("search_search")
}
