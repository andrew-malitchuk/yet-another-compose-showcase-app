package dev.yacsa.books.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yacsa.books.screen.about.AboutScreen
import dev.yacsa.books.screen.list.ListRoute
import dev.yacsa.navigation.NavigationDirection

fun NavGraphBuilder.booksNavGraph(navController: NavHostController) {
    navigation(
        startDestination = BooksDirection.List.route,
        route = NavigationDirection.Books.route
    ) {
        composable(BooksDirection.About.route) {
            AboutScreen() {}
        }
        composable(BooksDirection.List.route) {
            ListRoute(
                onClick = {
                    navController.navigate(BooksDirection.About.route)
                }
            )
        }
    }
}