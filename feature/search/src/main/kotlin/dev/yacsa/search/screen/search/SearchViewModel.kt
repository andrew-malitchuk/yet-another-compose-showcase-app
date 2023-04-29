package dev.yacsa.search.screen.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.usecase.books.SearchBooksUseCase
import dev.yacsa.model.mapper.BookUiDomainMapper
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: SearchUiState,
    private val searchBooksUseCase: SearchBooksUseCase,
    private val booksUiDomainMapper: BookUiDomainMapper
) : BaseViewModel<SearchUiState, SearchUiState.PartialState, SearchEvent, SearchIntent>(
    savedStateHandle,
    initialState,
) {
    val searchText = MutableStateFlow("")

    init {
        logcat { "init" }
        acceptIntent(SearchIntent.GetTopSearch)

        viewModelScope.launch {
            searchText
                .debounce(1_000L)
                .stateIn(
                    viewModelScope,
                    SharingStarted.WhileSubscribed(5_000L),
                    ""
                )
                .collect {
                    logcat("foo") { it }
                    if (it.isNotBlank()) {
                        acceptIntent(SearchIntent.Search(it))
                    }
                }
        }
    }

    override fun mapIntents(intent: SearchIntent): Flow<SearchUiState.PartialState> {
        return when (intent) {
            is SearchIntent.GetTopSearch -> getTopSearch()
            is SearchIntent.Search -> search(intent.query)
        }
    }

    private fun getTopSearch(): Flow<SearchUiState.PartialState> =
        flow<SearchUiState.PartialState> {
            // TODO: remove
            delay(1_000L)
            emit(SearchUiState.PartialState.ContentFetched(emptyList()))
        }.onStart {
            emit(SearchUiState.PartialState.ContentLoading)
        }

    private fun search(query: String): Flow<SearchUiState.PartialState> =
        flow<SearchUiState.PartialState> {
            val result = searchBooksUseCase(query).map(booksUiDomainMapper::toUi)
            emit(SearchUiState.PartialState.ResultFetched(result))
        }.onStart {
            emit(SearchUiState.PartialState.ResultLoading)
        }


    override fun reduceUiState(
        previousState: SearchUiState,
        partialState: SearchUiState.PartialState,
    ): SearchUiState {
        return when (partialState) {

            is SearchUiState.PartialState.ContentLoading -> previousState.copy(
                isContentLoading = true,
                isResultLoading = false,
                isError = false,
            )

            is SearchUiState.PartialState.ResultLoading -> previousState.copy(
                isContentLoading = false,
                isResultLoading = true,
                isError = false,
            )

            is SearchUiState.PartialState.ContentFetched -> previousState.copy(
                isContentLoading = false,
                isResultLoading = false,
                isError = false,
                topSearch = partialState.topSearch
            )

            is SearchUiState.PartialState.ResultFetched -> previousState.copy(
                isContentLoading = false,
                isResultLoading = false,
                isError = false,
                resultSearch = partialState.resultSearch
            )

            is SearchUiState.PartialState.Error -> previousState.copy(
                isContentLoading = false,
                isResultLoading = false,
                isError = true,
            )

        }
    }
}