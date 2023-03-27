package dev.yacsa.repository.impl.mapper.base

import dev.yacsa.datastore.model.base.BaseDataStoreModel
import dev.yacsa.repository.model.base.BaseRepoModel

abstract class RepoDataStoreMapper<Repo: BaseRepoModel, DataStore: BaseDataStoreModel> {
    abstract fun toRepo(value: DataStore):Repo
    abstract fun toDataStore(value: Repo):DataStore
}