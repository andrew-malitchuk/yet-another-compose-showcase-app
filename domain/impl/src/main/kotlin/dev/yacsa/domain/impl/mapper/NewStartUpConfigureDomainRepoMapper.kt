package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.repository.model.StartUpConfigureRepoModel
import org.mapstruct.Mapper

@Mapper
interface NewStartUpConfigureDomainRepoMapper {

    fun toRepo(
        startUpConfigureDomainModel: StartUpConfigureDomainModel,
    ): StartUpConfigureRepoModel

    fun toDomain(
        startUpConfigureRepoModel: StartUpConfigureRepoModel,
    ): StartUpConfigureDomainModel
}
