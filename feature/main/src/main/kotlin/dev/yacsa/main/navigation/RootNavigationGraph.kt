package dev.yacsa.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.yacsa.books.navigation.booksNavGraph
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.onboarding.navigation.onboardingNavGraph

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        route = NavigationDirection.Root.route,
        startDestination = startDestination
    ) {
        booksNavGraph(navController)
        onboardingNavGraph(navController)
    }

}