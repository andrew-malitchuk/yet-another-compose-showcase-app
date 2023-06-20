package dev.yacsa.info.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.yacsa.info.screen.info.InfoRoute
import dev.yacsa.navigation.NavigationDirection

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.infoNavGraph(navController: NavHostController) {
    navigation(
        startDestination = InfoDirections.Info.route,
        route = NavigationDirection.Info.route,
    ) {
        composable(
            InfoDirections.Info.route,
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
            InfoRoute(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
