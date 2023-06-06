package dev.yacsa.repository.repository

import dev.yacsa.repository.model.StartUpConfigureRepoModel
import dev.yacsa.repository.model.theme.ThemeRepoModel

interface StartUpConfigureRepository {
    suspend fun getStartUpConfigure(): StartUpConfigureRepoModel?
    suspend fun updateStartUpConfigure(value: StartUpConfigureRepoModel)
    suspend fun getTheme(): ThemeRepoModel
    suspend fun setTheme(theme:ThemeRepoModel)
}
