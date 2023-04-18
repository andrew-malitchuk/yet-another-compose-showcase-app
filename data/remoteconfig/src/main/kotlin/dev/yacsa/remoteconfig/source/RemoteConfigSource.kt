package dev.yacsa.remoteconfig.source

// TODO: add all other primitives etc
interface RemoteConfigSource {
    suspend fun getDouble(key: String): Result<Double>
    suspend fun getBoolean(key: String): Result<Boolean>
    suspend fun getString(key: String): Result<String>
}
