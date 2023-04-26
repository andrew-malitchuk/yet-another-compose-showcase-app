package dev.yacsa.repository.repository

import arrow.core.Either
import arrow.core.Option
import dev.yacsa.repository.error.DataError
import dev.yacsa.repository.model.StartUpConfigureRepoModel

object UserNotFound

data class UserAlreadyExists(val username: String, val email: String)

interface StartUpConfigureRepository {
    suspend fun getStartUpConfigure(): Either<DataError, StartUpConfigureRepoModel?>
    suspend fun updateStartUpConfigure(value: StartUpConfigureRepoModel):Option<DataError>
}
