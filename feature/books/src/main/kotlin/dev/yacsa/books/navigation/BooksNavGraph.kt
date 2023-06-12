package dev.yacsa.books.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import dev.yacsa.books.screen.detalization.DetalizationRoute
import dev.yacsa.books.screen.list.ListRoute
import dev.yacsa.navigation.NavigationDirection

fun NavGraphBuilder.booksNavGraph(navController: NavHostController) {
    navigation(
        startDestination = BooksDirection.List.route,
        route = NavigationDirection.Books.route,
    ) {
        composable(BooksDirection.List.route) {
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
                        NavigationDirection.Favourite.route
                    )
                }
            )
        }
        composable(
            BooksDirection.Detalization.route,
            deepLinks = listOf(
                // adb shell am start -W -a android.intent.action.VIEW -d yacsa://book/11 dev.yacsa.app.debug
                navDeepLink {
                    uriPattern="yacsa://book/{bookId}"
                }
            ),
            arguments = listOf(
                navArgument("bookId") { type = NavType.IntType },
            ),
        ) {
            DetalizationRoute(
                onBackClick = {
                    navController.popBackStack()
                },
            )
        }
    }
}
