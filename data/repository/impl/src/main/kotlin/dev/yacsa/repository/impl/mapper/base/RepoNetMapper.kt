package dev.yacsa.repository.impl.mapper.base

import dev.yacsa.datastore.model.base.BaseDataStoreModel
import dev.yacsa.network.model.base.BaseNetModel
import dev.yacsa.repository.model.base.BaseRepoModel

abstract class RepoNetMapper<Repo: BaseRepoModel, Net: BaseNetModel> {
    abstract fun toRepo(value: Net):Repo
    abstract fun toNet(value: Repo):Net
}