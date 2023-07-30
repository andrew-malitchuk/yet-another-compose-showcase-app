package dev.yacsa.repository.impl.impl

import arrow.core.Either
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dev.yacsa.remoteconfig.source.RemoteConfigSource
import dev.yacsa.repository.model.update.CheckUpdateRepoModel
import dev.yacsa.repository.repository.CheckUpdateRepository
import javax.inject.Inject

class CheckUpdateRepositoryImpl @Inject constructor(
    private val remoteConfigSource: RemoteConfigSource,
) : CheckUpdateRepository {

    override suspend fun checkUpdate(versionCode: String): Either<Exception, CheckUpdateRepoModel> {
        return try {
            val result = remoteConfigSource.getString("update")
            result.fold({
                Either.Left(it)
            }, {
                val moshi: Moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<CheckUpdateRepoModel> =
                    moshi.adapter(CheckUpdateRepoModel::class.java)
                val output = jsonAdapter.fromJson(it)
                Either.Right(output!!)
            })
        } catch (ex: Exception) {
            Either.Left(ex)
        }
    }
}
