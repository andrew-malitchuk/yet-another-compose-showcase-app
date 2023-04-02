package dev.yacsa.books.screen.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.books.screen.list.pagination.BooksSource
import dev.yacsa.domain.usecase.GetBooksUseCase
import dev.yacsa.domain.usecase.GetStartUpConfigureUseCase
import dev.yacsa.domain.usecase.LoadBooksUseCase
import dev.yacsa.domain.usecase.RefreshBooksUseCase
import dev.yacsa.model.mapper.BookUiDomainMapper
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getStartUpConfigureUseCase: GetStartUpConfigureUseCase,
    private val getBooksUseCase: GetBooksUseCase,
    private val loadBooksUseCase: LoadBooksUseCase,
    private val refreshBooksUseCase: RefreshBooksUseCase,
    private val bookUiDomainMapper: BookUiDomainMapper,
    savedStateHandle: SavedStateHandle,
    initialState: ListUiState
) : BaseViewModel<ListUiState, ListUiState.PartialState, ListEvent, ListIntent>(
    savedStateHandle,
    initialState
) {

    var fooFlow: MutableStateFlow<ListUiState.PartialState> = MutableStateFlow(ListUiState.PartialState.Loading)

    init {
        acceptIntent(ListIntent.GetBooks)
    }


    override fun mapIntents(intent: ListIntent): Flow<ListUiState.PartialState> {
        return when (intent) {
//            is ListIntent.GetBooks -> getBooks()
            is ListIntent.GetBooks -> fooFlow
            is ListIntent.RefreshBooks -> refreshBooks()
            is ListIntent.BookClicked -> bookClicked(intent.bookId)
        }
    }

    val foo :Flow<PagingData<BookUiModel>> = Pager(PagingConfig(pageSize = 32)) {
        BooksSource(
            getBooksUseCase,bookUiDomainMapper
        )
    }.flow

    private fun getBooks(): Flow<ListUiState.PartialState> = flow {
        loadBooksUseCase()
            .onStart {
                emit(ListUiState.PartialState.Loading)
            }
            .collect { result ->
                result
                    .onSuccess { list ->
                        emit(
                            ListUiState.PartialState.Fetched(
                                list.map(bookUiDomainMapper::toUi)
                            )
                        )
                    }
                    .onFailure {
                        emit(ListUiState.PartialState.Error(it))
                    }
            }
    }

    fun foo() {
        viewModelScope.launch {
            loadBooksUseCase()
                .onStart {
                    fooFlow.emit(ListUiState.PartialState.Loading)
                }
                .collect { result ->
                    result
                        .onSuccess { list ->
                            fooFlow.emit(
                                ListUiState.PartialState.Fetched(
                                    list.map(bookUiDomainMapper::toUi)
                                )
                            )
                        }
                        .onFailure {
                            fooFlow.emit(ListUiState.PartialState.Error(it))
                        }
                }
        }
    }

    var page=0
    fun bar() {
        page+=1
        viewModelScope.launch {
            getBooksUseCase(page)

        }
    }

    private fun refreshBooks(): Flow<ListUiState.PartialState> = flow {
        refreshBooksUseCase()
            .onFailure {
                emit(ListUiState.PartialState.Error(it))
            }
    }

    private fun bookClicked(bookId: Int): Flow<ListUiState.PartialState> {
        publishEvent(ListEvent.OnBookClick(bookId))
        return emptyFlow()
    }

    override fun reduceUiState(
        previousState: ListUiState,
        partialState: ListUiState.PartialState
    ): ListUiState {
        return when (partialState) {
            is ListUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false
            )
            is ListUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                books = partialState.list,
                isError = false
            )
            is ListUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true
            )
        }
    }

}