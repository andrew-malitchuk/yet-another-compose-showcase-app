package dev.yacsa.main.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import dev.yacsa.analytics.navigation.analyticsNavGraph
import dev.yacsa.books.navigation.booksNavGraph
import dev.yacsa.deeplink.navigation.deeplinkNavGraph
import dev.yacsa.favourite.navigation.favouriteNavGraph
import dev.yacsa.featureflagmanager.navigation.featureFlagNavGraph
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.notfound.navigation.notFoundNavGraph
import dev.yacsa.onboarding.navigation.onboardingNavGraph
import dev.yacsa.search.navigation.searchNavGraph
import dev.yacsa.settings.navigation.settingsNavGraph

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    startDestination: String,
) {
//    NavHost(
    AnimatedNavHost(
        navController = navController,
        route = NavigationDirection.Root.route,
        startDestination = startDestination,
    ) {
        booksNavGraph(navController)
        onboardingNavGraph(navController)
        featureFlagNavGraph(navController)
        notFoundNavGraph(navController)
        searchNavGraph(navController)
        settingsNavGraph(navController)
        analyticsNavGraph(navController)
        favouriteNavGraph(navController)
        deeplinkNavGraph(navController)
    }
}
