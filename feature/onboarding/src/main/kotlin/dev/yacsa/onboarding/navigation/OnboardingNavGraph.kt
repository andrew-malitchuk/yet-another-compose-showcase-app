package dev.yacsa.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.onboarding.screen.onboarding.OnboardingRoute

fun NavGraphBuilder.onboardingNavGraph(navController: NavHostController) {
    navigation(
        startDestination = OnboardingDirection.Onboarding.route,
        route = NavigationDirection.Onboarding.route,
    ) {
        composable(OnboardingDirection.Onboarding.route) {
            OnboardingRoute(
                onBackClick = {
                    navController.navigate("books")
                },
                onDoneClick = {
                    navController.navigate("books")
                },
            )
        }
    }
}
