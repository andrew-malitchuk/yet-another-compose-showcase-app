package dev.yacsa.onboarding.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.animation.composable
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.onboarding.screen.onboarding.OnboardingRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.onboardingNavGraph(navController: NavHostController) {
    navigation(
        startDestination = OnboardingDirection.Onboarding.route,
        route = NavigationDirection.Onboarding.route,
    ) {
        composable(
            OnboardingDirection.Onboarding.route,
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
