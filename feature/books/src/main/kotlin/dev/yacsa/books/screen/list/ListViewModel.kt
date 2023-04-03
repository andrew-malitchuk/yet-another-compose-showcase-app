package dev.yacsa.books.screen.list

import androidx.lifecycle.SavedStateHandle
import androidx.paging.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.books.screen.list.pagination.BooksSource
import dev.yacsa.domain.usecase.GetBooksUseCase
import dev.yacsa.model.mapper.BookUiDomainMapper
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val bookUiDomainMapper: BookUiDomainMapper,
    savedStateHandle: SavedStateHandle,
    initialState: ListUiState
) : BaseViewModel<ListUiState, ListUiState.PartialState, ListEvent, ListIntent>(
    savedStateHandle,
    initialState
) {

    init {
        acceptIntent(ListIntent.GetBooks)
    }

    override fun mapIntents(intent: ListIntent): Flow<ListUiState.PartialState> {
        return when (intent) {
            is ListIntent.GetBooks -> getBooks()
            is ListIntent.BookClicked -> bookClicked(intent.bookId)
        }
    }

    var pagingDataFlow: Flow<PagingData<BookUiModel>>? = null

    private fun getBooks(): Flow<ListUiState.PartialState> = flow<ListUiState.PartialState> {
        delay(5_000L)
        pagingDataFlow = Pager(PagingConfig(pageSize = 32)) {
            BooksSource(
                getBooksUseCase, bookUiDomainMapper
            )
        }.flow
        emit(ListUiState.PartialState.Fetched)
    }.onStart {
        emit(ListUiState.PartialState.Loading)
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
                isError = false
            )
            is ListUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true
            )
        }
    }

}