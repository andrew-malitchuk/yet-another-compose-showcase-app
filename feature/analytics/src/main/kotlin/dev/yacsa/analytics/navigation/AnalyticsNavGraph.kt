package dev.yacsa.analytics.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yacsa.analytics.screen.analytics.AnalyticsRoute
import dev.yacsa.navigation.NavigationDirection

fun NavGraphBuilder.analyticsNavGraph(navController: NavHostController) {
    navigation(
        startDestination = AnalyticsDirection.Analytics.route,
        route = NavigationDirection.Analytics.route,
    ) {
        composable(AnalyticsDirection.Analytics.route) {
            AnalyticsRoute(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
