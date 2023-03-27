package dev.yacsa.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.onboarding.OnboardingScreen

fun NavGraphBuilder.onboardingNavGraph(navController: NavHostController) {
    navigation(
        startDestination = OnboardingDirection.Onboarding.route,
        route = NavigationDirection.Onboarding.route
    ) {
        composable(OnboardingDirection.Onboarding.route) {
            OnboardingScreen(
                onBackClick = {
                    navController.navigate("books")

                },
                onDoneClick = {
                    navController.navigate("books")
                }
            )
        }
    }
}