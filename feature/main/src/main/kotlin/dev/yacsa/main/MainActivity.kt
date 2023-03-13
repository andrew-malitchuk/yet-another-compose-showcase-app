package dev.yacsa.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import dev.yacsa.main.navigation.RootNavigationGraph
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.ui.theme.YacsaTheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // TODO: add
//    @Inject
//    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isDarkTheme = true
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