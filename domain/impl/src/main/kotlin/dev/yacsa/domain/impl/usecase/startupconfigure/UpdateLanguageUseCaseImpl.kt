package dev.yacsa.domain.impl.usecase.startupconfigure

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.impl.mapper.NewStartUpConfigureDomainRepoMapper
import dev.yacsa.domain.usecase.startupconfigure.UpdateLanguageUseCase
import dev.yacsa.repository.repository.StartUpConfigureRepository
import javax.inject.Inject

class UpdateLanguageUseCaseImpl @Inject constructor(
    private val startUpConfigureDomainRepoMapper: NewStartUpConfigureDomainRepoMapper,
    private val startUpConfigureRepository: StartUpConfigureRepository,
) : UpdateLanguageUseCase {

    override suspend fun invoke(value: String): Option<DataError> {
        return try {
            startUpConfigureRepository.getStartUpConfigure()?.let { previous ->
                previous.language = value
                startUpConfigureRepository.updateStartUpConfigure(
                    previous
                )

            }
            none()
        } catch (ex: Exception) {
            DataError(ex).some()
        }
    }
}
