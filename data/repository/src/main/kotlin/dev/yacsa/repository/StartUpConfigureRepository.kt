package dev.yacsa.repository

import dev.yacsa.repository.model.StartUpConfigureRepoModel

interface StartUpConfigureRepository {
    suspend fun getStartUpConfigure(): StartUpConfigureRepoModel?
    suspend fun updateStartUpConfigure(value: StartUpConfigureRepoModel)
}
