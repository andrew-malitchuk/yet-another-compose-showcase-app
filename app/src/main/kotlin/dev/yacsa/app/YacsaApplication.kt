package dev.yacsa.app

import android.app.Application
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.navigation.NavigationFlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.soloader.SoLoader
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import dev.yacsa.featureflag.impl.container.FeatureFlagContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import logcat.AndroidLogcatLogger
import logcat.LogPriority
import javax.inject.Inject

@HiltAndroidApp
class YacsaApplication : Application() {

    @Inject
    lateinit var featureFlagContainer: FeatureFlagContainer

    @Inject
    lateinit var networkFlipperPlugin: NetworkFlipperPlugin

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.VERBOSE)
        FirebaseApp.initializeApp(this)
        applicationScope.launch {
            featureFlagContainer.sync()
        }
        configureFlipper()
    }

    private fun configureFlipper() {
        SoLoader.init(this, false)
        with(AndroidFlipperClient.getInstance(this)) {
            addPlugin(networkFlipperPlugin)
            addPlugin(NavigationFlipperPlugin.getInstance())
            start()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        applicationScope.cancel()
    }
}
