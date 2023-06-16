package dev.yacsa.main.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.yacsa.main.navigation.RootNavigationGraph
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.ui.theme.YacsaTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            val mainViewModel: MainViewModel = hiltViewModel()
            val navHostController = rememberNavController()
            val uiState by mainViewModel.uiState.collectAsStateWithLifecycle()
            splashScreen.setKeepOnScreenCondition { uiState.isLoading }
            val isDarkTheme = mainViewModel.theme.isDarkMode

            val startDestination = when {
                (!uiState.isLoading && !uiState.isError && uiState.routeDestination != null) -> {
                    when (uiState.routeDestination) {
                        RouteDestination.ONBOARDING -> NavigationDirection.Onboarding.route
                        RouteDestination.MAIN -> NavigationDirection.Books.route
                        else -> null
                    }
                }
                (uiState.isError) -> {
                    NavigationDirection.NotFound.route
                }

                else -> {
                    null
                }
            }
            YacsaTheme(
                isDarkTheme.value,
            ) {
                startDestination?.let {
                    RootNavigationGraph(
                        navController = navHostController,
                        startDestination = it,
                    )
                }
            }
        }
    }


}
