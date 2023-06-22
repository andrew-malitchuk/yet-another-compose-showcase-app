package dev.yacsa.settings.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.settings.screen.settings.SettingsRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.settingsNavGraph(navController: NavHostController) {
    navigation(
        startDestination = SettingsDirection.Settings.route,
        route = NavigationDirection.Settings.route,
    ) {
        composable(
            SettingsDirection.Settings.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(700),
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(700),
                )
            },
        ) {
            SettingsRoute(
                onBackClick = {
                    navController.popBackStack()
                },
                onFfClick = {
                    navController.navigate(NavigationDirection.FeatureFlag.route)
                },
                onAnalyticsClick = {
                    navController.navigate(NavigationDirection.Analytics.route)
                },
                onDeeplinkClick = {
                    navController.navigate(NavigationDirection.Deeplink.route)
                },
                onInfoClick = {
                    navController.navigate(NavigationDirection.Info.route)
                },
            )
        }
    }
}
