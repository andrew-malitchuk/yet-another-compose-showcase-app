package dev.yacsa.notfound.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.notfound.screen.notfound.NotFoundRoute

fun NavGraphBuilder.notFoundNavGraph(navController: NavHostController) {
    navigation(
        startDestination = NotFoundDirections.NotFound.route,
        route = NavigationDirection.NotFound.route,
    ) {
        composable(NotFoundDirections.NotFound.route) {
            NotFoundRoute(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
