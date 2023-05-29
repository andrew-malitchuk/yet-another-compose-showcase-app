package dev.yacsa.favourite.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yacsa.favourite.screen.favourite.FavouriteRoute
import dev.yacsa.navigation.NavigationDirection

fun NavGraphBuilder.favouriteNavGraph(navController: NavHostController) {
    navigation(
        startDestination = FavouriteDirection.Favourite.route,
        route = NavigationDirection.Favourite.route,
    ) {
        composable(FavouriteDirection.Favourite.route) {
            FavouriteRoute(
            )
        }
    }
}
