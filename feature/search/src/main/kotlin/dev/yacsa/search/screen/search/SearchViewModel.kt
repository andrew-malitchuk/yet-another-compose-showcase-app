package dev.yacsa.search.screen.search

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: SearchUiState,
) : BaseViewModel<SearchUiState, SearchUiState.PartialState, SearchEvent, SearchIntent>(
    savedStateHandle,
    initialState,
) {

    init {
        logcat { "init" }
        acceptIntent(SearchIntent.GetTopSearch)
    }


    override fun mapIntents(intent: SearchIntent): Flow<SearchUiState.PartialState> {
        return when (intent) {
            is SearchIntent.GetTopSearch -> TODO()
        }
    }

    override fun reduceUiState(
        previousState: SearchUiState,
        partialState: SearchUiState.PartialState,
    ): SearchUiState {
        return when (partialState) {
            is SearchUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
            )

            is SearchUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                isError = false,
                topSearch = partialState.topSearch
            )

            is SearchUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
            )

            is SearchUiState.PartialState.Result -> previousState.copy(
                isLoading = false,
                isError = false,
                resultSearch = partialState.resultSearch
            )
        }
    }
}