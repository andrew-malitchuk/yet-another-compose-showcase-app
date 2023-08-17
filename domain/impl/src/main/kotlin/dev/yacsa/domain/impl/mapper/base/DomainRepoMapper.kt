package dev.yacsa.domain.impl.mapper.base

import dev.yacsa.domain.model.base.BaseDomainModel
import dev.yacsa.repository.model.base.BaseRepoModel

abstract class DomainRepoMapper<Domain : BaseDomainModel, Repo : BaseRepoModel> {
    abstract fun toDomain(value: Repo): Domain
    abstract fun toRepo(value: Domain): Repo
}
