package dev.yacsa.repository.impl.mapper

import dev.yacsa.datastore.model.StartUpConfigureDataStoreModel
import dev.yacsa.repository.impl.mapper.base.RepoDataStoreMapper
import dev.yacsa.repository.model.StartUpConfigureRepoModel
import javax.inject.Inject

class StartUpConfigureRepoDataStoreMapper @Inject constructor() :
    RepoDataStoreMapper<StartUpConfigureRepoModel, StartUpConfigureDataStoreModel>() {

    override fun toRepo(value: StartUpConfigureDataStoreModel): StartUpConfigureRepoModel {
        return StartUpConfigureRepoModel(
            value.hasBeenOnboardingShown
        )
    }

    override fun toDataStore(value: StartUpConfigureRepoModel): StartUpConfigureDataStoreModel {
        return StartUpConfigureDataStoreModel(
            value.hasBeenOnboardingShown
        )
    }

}