package dev.yacsa.deeplink.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yacsa.deeplink.screen.deeplink.DeeplinkRoute
import dev.yacsa.navigation.NavigationDirection

fun NavGraphBuilder.deeplinkNavGraph(navController: NavHostController) {
    navigation(
        startDestination = DeeplinkDirection.Deeplink.route,
        route = NavigationDirection.Deeplink.route,
    ) {
        composable(DeeplinkDirection.Deeplink.route) {
            DeeplinkRoute(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
