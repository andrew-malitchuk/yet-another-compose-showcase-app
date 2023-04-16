package dev.yacsa.app

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import dev.yacsa.featureflag.impl.container.FeatureFlagContainer
import kotlinx.coroutines.*
import logcat.AndroidLogcatLogger
import logcat.LogPriority
import javax.inject.Inject

@HiltAndroidApp
class YacsaApplication : Application() {

    @Inject
    lateinit var featureFlagContainer:FeatureFlagContainer

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.VERBOSE)
        FirebaseApp.initializeApp(this)
        applicationScope.launch {
            featureFlagContainer.sync()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        applicationScope.cancel()
    }
}
