package dev.yacsa.search.screen.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.NoDataError
import dev.yacsa.domain.usecase.books.NewSearchBooksUseCase
import dev.yacsa.domain.usecase.history.NewClearHistoryUseCase
import dev.yacsa.domain.usecase.history.NewGetTopSearchUseCase
import dev.yacsa.domain.usecase.history.NewInsertSearchHistoryUseCase
import dev.yacsa.model.mapper.NewBooksUiDomainMapper
import dev.yacsa.model.mapper.NewSearchHistoryUiDomainMapper
import dev.yacsa.platform.viewmodel.BaseViewModel
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
    private val searchBooksUseCase: NewSearchBooksUseCase,
    private val booksUiDomainMapper: NewBooksUiDomainMapper,
    private val insertSearchHistoryUseCase: NewInsertSearchHistoryUseCase,
    private val getTopSearchUseCase: NewGetTopSearchUseCase,
    private val searchHistoryUiDomainMapper: NewSearchHistoryUiDomainMapper,
    private val clearHistoryUseCase: NewClearHistoryUseCase,
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
                    "",
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
            is SearchIntent.ClearSearch -> clearHistory()
        }
    }

    private fun getTopSearch(): Flow<SearchUiState.PartialState> =
        flow<SearchUiState.PartialState> {
            val result = getTopSearchUseCase()
            result.fold({ error ->
                when (error) {
                    is DataError -> {
                        emit(SearchUiState.PartialState.Error(error.throwable))
                    }

                    else -> {
                        emit(SearchUiState.PartialState.Error(Throwable("SWW")))
                    }
                }
            }, { result ->
                emit(
                    SearchUiState.PartialState.ContentFetched(
                        result.map(
                            searchHistoryUiDomainMapper::toUi,
                        ),
                    ),
                )
            })
        }.onStart {
            emit(SearchUiState.PartialState.ResultLoading)
        }

    private fun search(query: String): Flow<SearchUiState.PartialState> =
        flow {
            insertSearchHistoryUseCase(query).fold({
                searchBooksUseCase(query).fold(
                    { error ->
                        when (error) {
                            is NoDataError -> {
                                emit(SearchUiState.PartialState.ResultFetched(emptyList()))
                            }

                            is DataError -> {
                                emit(SearchUiState.PartialState.Error(error.throwable))
                            }

                            else -> {
                                emit(SearchUiState.PartialState.Error(Throwable("SWW")))
                            }
                        }
                    },
                    {
                        emit(SearchUiState.PartialState.ResultFetched(it.map(booksUiDomainMapper::toUi)))
                        acceptIntent(SearchIntent.GetTopSearch)
                    },
                )
            }, { error ->
                when (error) {
                    is DataError -> {
                        emit(SearchUiState.PartialState.Error(error.throwable))
                    }

                    else -> {
                        emit(SearchUiState.PartialState.Error(Throwable("SWW")))
                    }
                }
            })
        }.onStart {
            emit(SearchUiState.PartialState.ResultLoading)
        }

    private fun clearHistory(): Flow<SearchUiState.PartialState> =
        flow {
            clearHistoryUseCase().fold(
                {
                    emit(SearchUiState.PartialState.ContentFetched(emptyList()))
                },
                { error ->
                    when (error) {
                        is DataError -> {
                            emit(SearchUiState.PartialState.Error(error.throwable))
                        }

                        else -> {
                            emit(SearchUiState.PartialState.Error(Throwable("SWW")))
                        }
                    }
                },
            )
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
                topSearch = partialState.topSearch,
            )

            is SearchUiState.PartialState.ResultFetched -> previousState.copy(
                isContentLoading = false,
                isResultLoading = false,
                isError = false,
                resultSearch = partialState.resultSearch,
            )

            is SearchUiState.PartialState.Error -> previousState.copy(
                isContentLoading = false,
                isResultLoading = false,
                isError = true,
            )
        }
    }
}
