package dev.yacsa.ui.pagination

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}
