package dev.yacsa.featureflagmanager.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yacsa.featureflagmanager.screen.featureflagmanager.FeatureFlagRoute
import dev.yacsa.navigation.NavigationDirection

fun NavGraphBuilder.featureFlagNavGraph(navController: NavHostController) {
    navigation(
        startDestination = FeatureFlagDirections.List.route,
        route = NavigationDirection.FeatureFlag.route,
    ) {
        composable(FeatureFlagDirections.List.route) {
            FeatureFlagRoute()
        }
    }
}
