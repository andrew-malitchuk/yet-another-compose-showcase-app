package dev.yacsa.repository.impl.impl

import dev.yacsa.datastore.model.ThemeConfigureDataStoreModel
import dev.yacsa.datastore.model.ThemeMode
import dev.yacsa.datastore.source.StartupConfigureDataStoreSource
import dev.yacsa.datastore.source.ThemeConfigureDataStoreSource
import dev.yacsa.repository.impl.mapper.startup.NewStartUpConfigureRepoDataStoreMapper
import dev.yacsa.repository.model.StartUpConfigureRepoModel
import dev.yacsa.repository.model.theme.ThemeRepoModel
import dev.yacsa.repository.repository.StartUpConfigureRepository
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class StartUpConfigureRepositoryImpl @Inject constructor(
    private val startupConfigureDataStoreSource: StartupConfigureDataStoreSource,
    private val startUpConfigureMapper: NewStartUpConfigureRepoDataStoreMapper,
    private val themeConfigureDataStoreSource: ThemeConfigureDataStoreSource
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

    override suspend fun getTheme(): ThemeRepoModel {
        return themeConfigureDataStoreSource.getData().firstOrNull()?.let {
            ThemeRepoModel.valueOf(it.theme.name)
        } ?: ThemeRepoModel.LIGHT
    }

    override suspend fun setTheme(theme: ThemeRepoModel) {
        themeConfigureDataStoreSource.updateData(
            ThemeConfigureDataStoreModel(
                theme = ThemeMode.valueOf(theme.name)
            )
        )
    }
}
