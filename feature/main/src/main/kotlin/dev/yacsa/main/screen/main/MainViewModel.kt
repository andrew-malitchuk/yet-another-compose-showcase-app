package dev.yacsa.main.screen.main

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: MainUiState,
) : BaseViewModel<MainUiState, MainUiState.PartialState, MainEvent, MainIntent>(
    savedStateHandle,
    initialState,
){

    override fun mapIntents(intent: MainIntent): Flow<MainUiState.PartialState> {
        TODO("Not yet implemented")
    }

    override fun reduceUiState(
        previousState: MainUiState,
        partialState: MainUiState.PartialState
    ): MainUiState {
        TODO("Not yet implemented")
    }

}