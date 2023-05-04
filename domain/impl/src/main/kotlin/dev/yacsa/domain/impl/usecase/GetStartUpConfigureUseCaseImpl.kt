package dev.yacsa.domain.impl.usecase

import dev.yacsa.domain.impl.mapper.NewStartUpConfigureDomainRepoMapper
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.domain.usecase.GetStartUpConfigureUseCase
import dev.yacsa.repository.repository.StartUpConfigureRepository
import javax.inject.Inject

class GetStartUpConfigureUseCaseImpl @Inject constructor(
    private val startUpConfigureDomainRepoMapper: NewStartUpConfigureDomainRepoMapper,
    private val startUpConfigureRepository: StartUpConfigureRepository,
) : GetStartUpConfigureUseCase {

    override suspend fun invoke(): StartUpConfigureDomainModel? {
        return startUpConfigureRepository.getStartUpConfigure()
            ?.let { startUpConfigureDomainRepoMapper.toDomain(it) }
    }
}
