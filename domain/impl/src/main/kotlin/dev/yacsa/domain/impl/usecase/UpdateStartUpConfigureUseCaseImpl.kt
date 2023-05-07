package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.impl.mapper.NewStartUpConfigureDomainRepoMapper
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.domain.usecase.UpdateStartUpConfigureUseCase
import dev.yacsa.repository.repository.StartUpConfigureRepository
import javax.inject.Inject

class UpdateStartUpConfigureUseCaseImpl @Inject constructor(
    private val startUpConfigureDomainRepoMapper: NewStartUpConfigureDomainRepoMapper,
    private val startUpConfigureRepository: StartUpConfigureRepository,
) : UpdateStartUpConfigureUseCase {

    override suspend fun invoke(value: StartUpConfigureDomainModel) {
        startUpConfigureRepository.updateStartUpConfigure(
            startUpConfigureDomainRepoMapper.toRepo(
                value,
            ),
        )
    }
}
