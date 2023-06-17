package dev.yacsa.favourite.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.yacsa.favourite.screen.favourite.FavouriteRoute
import dev.yacsa.navigation.NavigationDirection

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.favouriteNavGraph(navController: NavHostController) {
    navigation(
        startDestination = FavouriteDirection.Favourite.route,
        route = NavigationDirection.Favourite.route,
    ) {
        composable(
            FavouriteDirection.Favourite.route,
            deepLinks = listOf(
                // adb shell am start -W -a android.intent.action.VIEW -d yacsa://favourite dev.yacsa.app.debug
                navDeepLink {
                    uriPattern="yacsa://favourite"
                }
            ),
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
            FavouriteRoute(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
