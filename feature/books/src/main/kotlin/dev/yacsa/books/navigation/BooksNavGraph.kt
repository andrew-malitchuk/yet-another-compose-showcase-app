package dev.yacsa.books.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import dev.yacsa.books.screen.about.AboutScreen
import dev.yacsa.books.screen.detalization.DetalizationRoute
import dev.yacsa.books.screen.list.ListRoute
import dev.yacsa.navigation.NavigationDirection

fun NavGraphBuilder.booksNavGraph(navController: NavHostController) {
    navigation(
        startDestination = BooksDirection.List.route,
        route = NavigationDirection.Books.route,
    ) {
        composable(BooksDirection.About.route) {
            AboutScreen() {}
        }
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
                onFeatureFlagClick = {
                    navController.navigate(
                        NavigationDirection.FeatureFlag.route
                    )
                }
            )
        }
        composable(
            BooksDirection.Detalization.route,
            arguments = listOf(
                navArgument("bookId") { type = NavType.IntType },
            ),
        ) { backStackEntry ->
            DetalizationRoute(
                bookId = backStackEntry.arguments?.getInt("bookId"),
            )
        }
    }
}
