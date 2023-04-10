package dev.yacsa.remoteconfig.impl.config

import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import javax.inject.Inject

class RemoteConfigure @Inject constructor(
) {

    val remoteConfig: FirebaseRemoteConfig by lazy {
        Firebase.remoteConfig
    }

    private val configSettings by lazy {
        FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds((if (BuildConfig.DEBUG) 0 else FETCH_INTERVAL))
            .build()
    }

    init {
        with(remoteConfig) {
            setConfigSettingsAsync(configSettings)
        }
    }

    companion object {
        const val FETCH_INTERVAL = 3_600L
    }

}