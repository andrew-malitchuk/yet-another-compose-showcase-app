package dev.yacsa.analytics.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.yacsa.analytics.screen.analytics.AnalyticsRoute
import dev.yacsa.navigation.NavigationDirection

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.analyticsNavGraph(navController: NavHostController) {
    navigation(
        startDestination = AnalyticsDirection.Analytics.route,
        route = NavigationDirection.Analytics.route,
    ) {
        composable(
            AnalyticsDirection.Analytics.route,
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
            AnalyticsRoute(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
