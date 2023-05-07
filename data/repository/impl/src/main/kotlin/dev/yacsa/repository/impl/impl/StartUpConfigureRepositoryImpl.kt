package dev.yacsa.repository.impl.impl

import dev.yacsa.datastore.source.StartupConfigureDataStoreSource
import dev.yacsa.repository.impl.mapper.startup.NewStartUpConfigureRepoDataStoreMapper
import dev.yacsa.repository.model.StartUpConfigureRepoModel
import dev.yacsa.repository.repository.StartUpConfigureRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class StartUpConfigureRepositoryImpl @Inject constructor(
    private val startupConfigureDataStoreSource: StartupConfigureDataStoreSource,
    private val startUpConfigureMapper: NewStartUpConfigureRepoDataStoreMapper,
) : StartUpConfigureRepository {

    override suspend fun getStartUpConfigure(): StartUpConfigureRepoModel? {
        return startupConfigureDataStoreSource.getData().firstOrNull()?.let {
            startUpConfigureMapper.toRepo(
                it,
            )
        }
    }

    override suspend fun updateStartUpConfigure(value: StartUpConfigureRepoModel) {
        startupConfigureDataStoreSource.updateData(startUpConfigureMapper.toDataStore(value))
    }
}
