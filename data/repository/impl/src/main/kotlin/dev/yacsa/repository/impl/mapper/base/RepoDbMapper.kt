package dev.yacsa.repository.impl.mapper.base

import dev.yacsa.database.model.base.BaseDbModel
import dev.yacsa.network.model.base.BaseNetModel
import dev.yacsa.repository.model.base.BaseRepoModel

abstract class RepoDbMapper<Repo : BaseRepoModel, Db : BaseDbModel> {
    abstract fun toRepo(value: Db): Repo
    abstract fun toDb(value: Repo): Db
}
