package dev.yacsa.favourite.screen.favourite

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: FavouriteUiState,
) : BaseViewModel<FavouriteUiState, FavouriteUiState.PartialState, FavouriteEvent, FavouriteIntent>(
    savedStateHandle,
    initialState,
) {
    override fun mapIntents(intent: FavouriteIntent): Flow<FavouriteUiState.PartialState> {
        TODO("Not yet implemented")
    }

    override fun reduceUiState(
        previousState: FavouriteUiState,
        partialState: FavouriteUiState.PartialState
    ): FavouriteUiState {
        TODO("Not yet implemented")
    }

}