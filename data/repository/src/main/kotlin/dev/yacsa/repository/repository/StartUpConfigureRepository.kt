package dev.yacsa.repository.repository

import dev.yacsa.repository.model.StartUpConfigureRepoModel

interface StartUpConfigureRepository {
    suspend fun getStartUpConfigure(): StartUpConfigureRepoModel?
    suspend fun updateStartUpConfigure(value: StartUpConfigureRepoModel)
}
