package dev.yacsa.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.search.screen.search.SearchRoute

fun NavGraphBuilder.searchNavGraph(navController: NavHostController) {
    navigation(
        startDestination = SearchDirection.Search.route,
        route = NavigationDirection.Search.route,
    ) {
        composable(SearchDirection.Search.route) {
            SearchRoute(
                onBookClicked= {
                    navController.navigate(
                        "books_detalization/$it",
                    ) {
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
