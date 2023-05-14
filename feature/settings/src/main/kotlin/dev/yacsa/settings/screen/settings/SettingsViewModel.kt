package dev.yacsa.settings.screen.settings

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: SettingsUiState,
) : BaseViewModel<SettingsUiState, SettingsUiState.PartialState, SettingsEvent, SettingsIntent>(
    savedStateHandle,
    initialState,
) {

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
}
