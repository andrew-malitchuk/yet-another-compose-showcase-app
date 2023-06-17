package dev.yacsa.notfound.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.notfound.screen.notfound.NotFoundRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.notFoundNavGraph(navController: NavHostController) {
    navigation(
        startDestination = NotFoundDirections.NotFound.route,
        route = NavigationDirection.NotFound.route,
    ) {
        composable(
            NotFoundDirections.NotFound.route,
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
            NotFoundRoute(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
