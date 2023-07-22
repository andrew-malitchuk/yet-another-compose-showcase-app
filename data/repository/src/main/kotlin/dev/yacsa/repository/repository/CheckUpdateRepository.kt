package dev.yacsa.repository.repository

import arrow.core.Either
import dev.yacsa.repository.model.update.CheckUpdateRepoModel

interface CheckUpdateRepository {
    suspend fun checkUpdate(versionCode:String): Either<Exception, CheckUpdateRepoModel>
}