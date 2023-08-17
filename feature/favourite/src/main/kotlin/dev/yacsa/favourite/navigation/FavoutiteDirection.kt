package dev.yacsa.favourite.navigation

sealed class FavouriteDirection(var route: String) {
    object Favourite : FavouriteDirection("favourite_favourite")
}
