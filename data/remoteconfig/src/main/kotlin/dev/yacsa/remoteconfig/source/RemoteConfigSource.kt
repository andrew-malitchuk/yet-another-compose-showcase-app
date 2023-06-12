package dev.yacsa.remoteconfig.source

import arrow.core.Either

// TODO: add all other primitives etc
interface RemoteConfigSource {
    suspend fun getDouble(key: String): Either<Exception,Double>
    suspend fun getBoolean(key: String): Either<Exception,Boolean>
    suspend fun getString(key: String): Either<Exception,String>
}
