package dev.yacsa.books.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.yacsa.books.screen.detalization.DetalizationRoute
import dev.yacsa.books.screen.list.ListRoute
import dev.yacsa.navigation.NavigationDirection

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.booksNavGraph(navController: NavHostController) {
    navigation(
        startDestination = BooksDirection.List.route,
        route = NavigationDirection.Books.route,
    ) {
        composable(
            BooksDirection.List.route,
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
            ListRoute(
                onClick = {
                    navController.navigate(
                        BooksDirection.Detalization.getRoute(it),
                    ) {
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                onSearch = {
                    navController.navigate(
                        NavigationDirection.Search.route,
                    )
                },
                notFound = {
                    navController.navigate(
                        NavigationDirection.NotFound.route,
                    )
                },
                onSettings = {
                    navController.navigate(
                        NavigationDirection.Settings.route,
                    )
                },
                onFavourite = {
                    navController.navigate(
                        NavigationDirection.Favourite.route,
                    )
                },
                onBookClicked = {
                    navController.navigate(
                        BooksDirection.Detalization.getRoute(it),
                    )
                },
            )
        }
        composable(
            BooksDirection.Detalization.route,
            deepLinks = listOf(
                // adb shell am start -W -a android.intent.action.VIEW -d yacsa://book/11 dev.yacsa.app.debug
                navDeepLink {
                    uriPattern = "yacsa://book/{bookId}"
                },
            ),
            arguments = listOf(
                navArgument("bookId") { type = NavType.IntType },
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
            DetalizationRoute(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
