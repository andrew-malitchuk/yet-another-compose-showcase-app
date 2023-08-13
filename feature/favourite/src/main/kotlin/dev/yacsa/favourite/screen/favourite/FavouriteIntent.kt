package dev.yacsa.favourite.screen.favourite

sealed class FavouriteIntent {
    object GetFavourite : FavouriteIntent()
    data class MarkFavourite(val bookId: Int, val isFavourite: Boolean) : FavouriteIntent()
}
