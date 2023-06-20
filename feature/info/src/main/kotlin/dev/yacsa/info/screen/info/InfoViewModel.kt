package dev.yacsa.info.screen.info

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.platform.Theme
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: InfoUiState,
    private val theme:Theme,
    ) : BaseViewModel<InfoUiState, InfoUiState.PartialState, InfoEvent, InfoIntent>(
    savedStateHandle,
    initialState
) , Theme by theme{

    init {
        logcat { "init" }
        acceptIntent(InfoIntent.GetInfos)
    }

    private fun getAllInfos(): Flow<InfoUiState.PartialState> =
        flow {
        }

    override fun mapIntents(intent: InfoIntent): Flow<InfoUiState.PartialState> {
        return when (intent) {
            is InfoIntent.GetInfos -> getAllInfos()
            is InfoIntent.OnInfoClick -> TODO()
        }
    }

    override fun reduceUiState(
        previousState: InfoUiState,
        partialState: InfoUiState.PartialState,
    ): InfoUiState {
        return when (partialState) {
            is InfoUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
            )

            is InfoUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                isError = false,
            )

            is InfoUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
            )
        }
    }
}
