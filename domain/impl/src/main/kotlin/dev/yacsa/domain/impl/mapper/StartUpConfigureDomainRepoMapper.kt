package dev.yacsa.domain.impl.mapper

import dev.yacsa.domain.impl.mapper.base.DomainRepoMapper
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.repository.model.StartUpConfigureRepoModel
import javax.inject.Inject

class StartUpConfigureDomainRepoMapper @Inject constructor() :
    DomainRepoMapper<StartUpConfigureDomainModel, StartUpConfigureRepoModel>() {

    override fun toDomain(value: StartUpConfigureRepoModel): StartUpConfigureDomainModel {
        return StartUpConfigureDomainModel(value.hasBeenOnboardingShown)
    }

    override fun toRepo(value: StartUpConfigureDomainModel): StartUpConfigureRepoModel {
        return StartUpConfigureRepoModel(value.hasBeenOnboardingShown)
    }
}
