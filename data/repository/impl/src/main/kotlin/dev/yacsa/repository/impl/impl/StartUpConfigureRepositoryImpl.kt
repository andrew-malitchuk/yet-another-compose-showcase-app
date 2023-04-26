package dev.yacsa.repository.impl.impl

import arrow.core.Either
import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.datastore.source.StartupConfigureDataStoreSource
import dev.yacsa.repository.error.DataError
import dev.yacsa.repository.error.DatastoreError
import dev.yacsa.repository.impl.mapper.StartUpConfigureRepoDataStoreMapper
import dev.yacsa.repository.model.StartUpConfigureRepoModel
import dev.yacsa.repository.repository.StartUpConfigureRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class StartUpConfigureRepositoryImpl @Inject constructor(
    private val startupConfigureDataStoreSource: StartupConfigureDataStoreSource,
    private val startUpConfigureMapper: StartUpConfigureRepoDataStoreMapper,
) : StartUpConfigureRepository {

    override suspend fun getStartUpConfigure(): Either<DataError, StartUpConfigureRepoModel?> {
        return try {
            val result = startupConfigureDataStoreSource.getData().firstOrNull()
                ?.let(startUpConfigureMapper::toRepo)
            Either.Right(result)
        } catch (ex: Exception) {
            Either.Left(DatastoreError.DatastoreIoError(ex))
        }
    }

    // TODO: maybe, in future, recode to Raise but right now, i have an error with it https://github.com/arrow-kt/arrow/issues/3041
    override suspend fun updateStartUpConfigure(value: StartUpConfigureRepoModel): Option<DataError> {
        return try {
            startupConfigureDataStoreSource.updateData(startUpConfigureMapper.toDataStore(value))
            none()
        } catch (ex: Exception) {
            DatastoreError.DatastoreIoError(ex).some()
        }
    }
}
