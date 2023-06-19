package dev.yacsa.settings.screen.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.usecase.startupconfigure.GetLanguageUseCase
import dev.yacsa.domain.usecase.startupconfigure.UpdateLanguageUseCase
import dev.yacsa.model.model.theme.ThemeUiModel
import dev.yacsa.platform.Theme
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val theme: Theme,
    private val updateLanguageUseCase: UpdateLanguageUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    savedStateHandle: SavedStateHandle,
    initialState: SettingsUiState,
) : BaseViewModel<SettingsUiState, SettingsUiState.PartialState, SettingsEvent, SettingsIntent>(
    savedStateHandle,
    initialState,
), Theme by theme {

    init{
        this.acceptIntent(SettingsIntent.GetLang)
    }

    override fun mapIntents(intent: SettingsIntent): Flow<SettingsUiState.PartialState> {
        return when (intent) {
            is SettingsIntent.GetTheme -> TODO()
            is SettingsIntent.GetLang -> getLang()
            is SettingsIntent.SetLang -> setLang(intent.lang)
        }
    }

    private fun getLang(): Flow<SettingsUiState.PartialState> =
        flow {
            getLanguageUseCase().fold(
                {
                    // TODO: fix
                    emit(SettingsUiState.PartialState.Error(Exception()))
                },
                { result ->
                    emit(SettingsUiState.PartialState.Fetched(result))
                }
            )
        }.onStart {
            SettingsUiState.PartialState.Loading
        }

    private fun setLang(lang:String): Flow<SettingsUiState.PartialState> =
        flow {
            updateLanguageUseCase(lang).fold({
                publishEvent(SettingsEvent.ChangeLang(lang))
            },{
                emit(SettingsUiState.PartialState.Error(Exception()))
            })
        }.onStart {
            SettingsUiState.PartialState.Loading
        }

    override fun reduceUiState(
        previousState: SettingsUiState,
        partialState: SettingsUiState.PartialState,
    ): SettingsUiState {
        return when (partialState) {
            is SettingsUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
            )
            is SettingsUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                isError = false,
                lang = partialState.lang
            )

            SettingsUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
            )
        }
    }

    fun changeTheme(themeUiModel: ThemeUiModel) {
        viewModelScope.launch {
            theme.setTheme(themeUiModel)
        }
    }

}
