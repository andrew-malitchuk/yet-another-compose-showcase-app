package dev.yacsa.domain.impl.mapper.update

import dev.yacsa.domain.model.update.CheckUpdateDomainModel
import dev.yacsa.repository.model.update.CheckUpdateRepoModel
import org.mapstruct.Mapper

@Mapper
interface CheckUpdateDomainRepoMapper {

    fun toRepo(
        checkUpdateDomainModel: CheckUpdateDomainModel,
    ): CheckUpdateRepoModel

    fun toDomain(
        checkUpdateRepoModel: CheckUpdateRepoModel,
    ): CheckUpdateDomainModel
}
