package dev.yacsa.search.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.search.screen.search.SearchRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchNavGraph(navController: NavHostController) {
    navigation(
        startDestination = SearchDirection.Search.route,
        route = NavigationDirection.Search.route,
    ) {
        composable(
            SearchDirection.Search.route,
            deepLinks = listOf(
                // adb shell am start -W -a android.intent.action.VIEW -d yacsa://search dev.yacsa.app.debug
                navDeepLink {
                    uriPattern = "yacsa://search"
                },
            ),
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
            SearchRoute(
                onBookClicked = {
                    navController.navigate(
                        "books_detalization/$it",
                    ) {
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
