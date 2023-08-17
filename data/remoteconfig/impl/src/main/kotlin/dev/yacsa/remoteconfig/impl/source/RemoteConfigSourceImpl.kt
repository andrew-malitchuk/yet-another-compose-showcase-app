package dev.yacsa.remoteconfig.impl.source

import arrow.core.Either
import dev.yacsa.remoteconfig.impl.config.RemoteConfigure
import dev.yacsa.remoteconfig.source.RemoteConfigSource
import logcat.logcat
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RemoteConfigSourceImpl @Inject constructor(
    private val remoteConfig: RemoteConfigure,
) : RemoteConfigSource {

    override suspend fun getDouble(key: String): Either<Exception, Double> {
        return suspendCoroutine { continuation ->
            try {
                remoteConfig.remoteConfig.apply {
                    fetchAndActivate().addOnCompleteListener {
                        if (it.isSuccessful) {
                            val value = remoteConfig.remoteConfig.getDouble(key)
                            logcat { "$key: $value" }
                            continuation.resume(Either.Right(value))
                        } else {
                            continuation.resume(Either.Left(IOException()))
                        }
                    }
                }
            } catch (ex: Exception) {
                continuation.resume(Either.Left(ex))
            }
        }
    }

    override suspend fun getBoolean(key: String): Either<Exception, Boolean> {
        return suspendCoroutine { continuation ->
            try {
                remoteConfig.remoteConfig.apply {
                    fetchAndActivate().addOnCompleteListener {
                        if (it.isSuccessful) {
                            val value = remoteConfig.remoteConfig.getBoolean(key)
                            logcat { "$key: $value" }
                            continuation.resume(Either.Right(value))
                        } else {
                            continuation.resume(Either.Left(IOException()))
                        }
                    }
                }
            } catch (ex: Exception) {
                continuation.resume(Either.Left(ex))
            }
        }
    }

    override suspend fun getString(key: String): Either<Exception, String> {
        return suspendCoroutine { continuation ->
            try {
                remoteConfig.remoteConfig.apply {
                    fetchAndActivate().addOnCompleteListener {
                        if (it.isSuccessful) {
                            val value = remoteConfig.remoteConfig.getString(key)
                            logcat { "$key: $value" }
                            continuation.resume(Either.Right(value))
                        } else {
                            continuation.resume(Either.Left(IOException()))
                        }
                    }
                }
            } catch (ex: Exception) {
                continuation.resume(Either.Left(ex))
            }
        }
    }
}
