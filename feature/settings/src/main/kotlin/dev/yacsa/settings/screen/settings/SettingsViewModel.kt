package dev.yacsa.settings.screen.settings

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.model.model.theme.ThemeUiModel
import dev.yacsa.platform.Theme
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val theme:Theme,
    savedStateHandle: SavedStateHandle,
    initialState: SettingsUiState,
) : BaseViewModel<SettingsUiState, SettingsUiState.PartialState, SettingsEvent, SettingsIntent>(
    savedStateHandle,
    initialState,
), Theme by theme{

    override fun mapIntents(intent: SettingsIntent): Flow<SettingsUiState.PartialState> {
        return when (intent) {
            is SettingsIntent.GetTheme -> TODO()
        }
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

            SettingsUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                isError = false,
            )

            SettingsUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
            )
        }
    }

    fun changeTheme(themeUiModel: ThemeUiModel){
        viewModelScope.launch {
            theme.setTheme(themeUiModel)
        }
    }

}
