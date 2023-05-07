package dev.yacsa.domain.impl.usecase.startupconfigure

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.impl.mapper.StartUpConfigureDomainRepoMapper
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.domain.usecase.startupconfigure.NewUpdateStartUpConfigureUseCase
import dev.yacsa.repository.repository.StartUpConfigureRepository
import javax.inject.Inject

class NewUpdateStartUpConfigureUseCaseImpl @Inject constructor(
    private val startUpConfigureDomainRepoMapper: StartUpConfigureDomainRepoMapper,
    private val startUpConfigureRepository: StartUpConfigureRepository,
) : NewUpdateStartUpConfigureUseCase {

    override suspend fun invoke(value: StartUpConfigureDomainModel): Option<DataError> {
        return try {
            startUpConfigureRepository.updateStartUpConfigure(
                startUpConfigureDomainRepoMapper.toRepo(
                    value,
                ),
            )
            none()
        } catch (ex: Exception) {
            DataError(ex).some()
        }

    }
}
