package dev.yacsa.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dev.yacsa.main.navigation.RootNavigationGraph
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // TODO: add
//    @Inject
//    lateinit var networkMonitor: NetworkMonitor

    var isSplashShown: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        val isDarkTheme = true
        splashScreen.setKeepOnScreenCondition {
            isSplashShown
        }
        GlobalScope.launch(Dispatchers.Main) {
            delay(1_000L)
            isSplashShown=false
        }
        setContent {
            val navHostController = rememberNavController()
            YacsaTheme(
                isDarkTheme
            ) {
                val startDestination = NavigationDirection.Onboarding.route
//                val startDestination = NavigationDirection.Books.route
                RootNavigationGraph(
                    navController = navHostController,
                    startDestination = startDestination
                )
            }
        }
    }

}