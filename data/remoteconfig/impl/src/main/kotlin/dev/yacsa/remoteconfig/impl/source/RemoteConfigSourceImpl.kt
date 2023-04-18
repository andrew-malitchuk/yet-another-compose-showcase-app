package dev.yacsa.remoteconfig.impl.source

import dev.yacsa.remoteconfig.impl.config.RemoteConfigure
import dev.yacsa.remoteconfig.source.RemoteConfigSource
import logcat.logcat
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RemoteConfigSourceImpl @Inject constructor(
    private val remoteConfig: RemoteConfigure
) : RemoteConfigSource {

    override suspend fun getDouble(key: String): Result<Double> {
        return suspendCoroutine { continuation ->
            try {
                remoteConfig.remoteConfig.apply {
                    reset()
                    fetchAndActivate().addOnCompleteListener {
                        if (it.isSuccessful) {
                            val value = remoteConfig.remoteConfig.getDouble(key)
                            logcat { "$key: $value" }
                            continuation.resume(Result.success(value))
                        } else {
                            continuation.resume(Result.failure(IOException()))
                        }
                    }
                }
            } catch (ex: Exception) {
                continuation.resume(Result.failure(ex))
            }
        }
    }

    override suspend fun getBoolean(key: String): Result<Boolean> {
        return suspendCoroutine { continuation ->
            try {
                remoteConfig.remoteConfig.apply {
                    reset()
                    fetchAndActivate().addOnCompleteListener {
                        if (it.isSuccessful) {
                            val value = remoteConfig.remoteConfig.getBoolean(key)
                            logcat { "$key: $value" }
                            continuation.resume(Result.success(value))
                        } else {
                            continuation.resume(Result.failure(IOException()))
                        }
                    }
                }
            } catch (ex: Exception) {
                continuation.resume(Result.failure(ex))
            }
        }
    }

    override suspend fun getString(key: String): Result<String> {
        return suspendCoroutine { continuation ->
            try {
                remoteConfig.remoteConfig.apply {
                    reset()
                    fetchAndActivate().addOnCompleteListener {
                        if (it.isSuccessful) {
                            val value = remoteConfig.remoteConfig.getString(key)
                            logcat { "$key: $value" }
                            continuation.resume(Result.success(value))
                        } else {
                            continuation.resume(Result.failure(IOException()))
                        }
                    }
                }
            } catch (ex: Exception) {
                continuation.resume(Result.failure(ex))
            }
        }
    }


}