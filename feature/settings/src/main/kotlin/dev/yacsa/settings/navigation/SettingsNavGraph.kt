package dev.yacsa.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.settings.screen.settings.SettingsRoute

fun NavGraphBuilder.settingsNavGraph(navController: NavHostController) {
    navigation(
        startDestination = SettingsDirection.Settings.route,
        route = NavigationDirection.Settings.route,
    ) {
        composable(SettingsDirection.Settings.route) {
            SettingsRoute(

            )
        }
    }
}