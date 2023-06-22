package dev.yacsa.featureflagmanager.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.yacsa.featureflagmanager.screen.featureflagmanager.FeatureFlagRoute
import dev.yacsa.navigation.NavigationDirection

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.featureFlagNavGraph(navController: NavHostController) {
    navigation(
        startDestination = FeatureFlagDirections.List.route,
        route = NavigationDirection.FeatureFlag.route,
    ) {
        composable(
            FeatureFlagDirections.List.route,
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
            FeatureFlagRoute(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
