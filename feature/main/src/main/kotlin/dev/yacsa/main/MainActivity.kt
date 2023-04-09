package dev.yacsa.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.yacsa.cryptodatastore.source.AccessTokenCryptoDataStoreSource
import dev.yacsa.datastore.source.PreferencesDataStoreSource
import dev.yacsa.main.navigation.RootNavigationGraph
import dev.yacsa.navigation.NavigationDirection
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var prefereDataSource: PreferencesDataStoreSource

    @Inject
    lateinit var accessTokenCryptoDataStoreSource: AccessTokenCryptoDataStoreSource

    // TODO: add
//    @Inject
//    lateinit var networkMonitor: NetworkMonitor

    var isSplashShown: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        val isDarkTheme = false
        splashScreen.setKeepOnScreenCondition {
            isSplashShown
        }
        GlobalScope.launch(Dispatchers.Main) {
            delay(1_000L)
            isSplashShown = false
        }
        setContent {
            val navHostController = rememberNavController()
            YacsaTheme(
                isDarkTheme,
            ) {
                val startDestination = NavigationDirection.Onboarding.route
//                val startDestination = NavigationDirection.Books.route
                RootNavigationGraph(
                    navController = navHostController,
                    startDestination = startDestination,
                )
            }
        }
    }
}
