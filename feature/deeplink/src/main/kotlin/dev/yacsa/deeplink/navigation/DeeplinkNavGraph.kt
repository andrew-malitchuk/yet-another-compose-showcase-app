package dev.yacsa.deeplink.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.yacsa.deeplink.screen.deeplink.DeeplinkRoute
import dev.yacsa.navigation.NavigationDirection

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.deeplinkNavGraph(navController: NavHostController) {
    navigation(
        startDestination = DeeplinkDirection.Deeplink.route,
        route = NavigationDirection.Deeplink.route,
    ) {
        composable(
            DeeplinkDirection.Deeplink.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700)
                )
            }
        ) {
            DeeplinkRoute(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
