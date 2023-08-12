package dev.yacsa.deeplink.screen.deeplink

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.platform.Theme
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class DeeplinkViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: DeeplinkUiState,
    private val theme: Theme,
) : BaseViewModel<DeeplinkUiState, DeeplinkUiState.PartialState, DeeplinkEvent, DeeplinkIntent>(
    savedStateHandle,
    initialState,
),
    Theme by theme {

    init {
    }

    override fun mapIntents(intent: DeeplinkIntent): Flow<DeeplinkUiState.PartialState> {
//        return when (intent) {
//            is DeeplinkIntent.GetList -> getDeeplink()
//            is DeeplinkIntent.Delete -> clear()
//        }
        return emptyFlow()
    }

    override fun reduceUiState(
        previousState: DeeplinkUiState,
        partialState: DeeplinkUiState.PartialState,
    ): DeeplinkUiState {
        return when (partialState) {
            is DeeplinkUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
            )

            is DeeplinkUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                isError = false,
            )

            DeeplinkUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
            )
        }
    }
}
