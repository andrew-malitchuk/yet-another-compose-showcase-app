package dev.yacsa.books.screen.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.books.screen.list.pagination.BookRemoteSource
import dev.yacsa.domain.usecase.*
import dev.yacsa.model.mapper.BookUiDomainMapper
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val bookUiDomainMapper: BookUiDomainMapper,
    private val getRemoteKeyByBookId: GetRemoteKeyByBookId,
    private val removeRemoteKeyUseCase: RemoveRemoteKeyUseCase,
    private val removeAllBooksUseCase: RemoveAllBooksUseCase,
    private val addAllRemoteKeysUseCase: AddAllRemoteKeysUseCase,
    private val saveBooksUseCase: SaveBooksUseCase,
    savedStateHandle: SavedStateHandle,
    initialState: ListUiState
) : BaseViewModel<ListUiState, ListUiState.PartialState, ListEvent, ListIntent>(
    savedStateHandle,
    initialState
) {

    init {
        logcat { "init" }
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
        logcat { "getBooks" }
//        delay(5_000L)
//        pagingDataFlow = Pager(PagingConfig(pageSize = 32)) {
//            BooksSource(
//                getBooksUseCase, bookUiDomainMapper
//            )
//        }.flow.cachedIn(viewModelScope)
        pagingDataFlow = Pager(
            config = PagingConfig(pageSize = 32),
            pagingSourceFactory = {

            },
            remoteMediator = BookRemoteSource(
                1,
                getBooksUseCase,
                getRemoteKeyByBookId,
                removeRemoteKeyUseCase,
                removeAllBooksUseCase,
                addAllRemoteKeysUseCase,
                saveBooksUseCase,
            )

            ).flow.cachedIn(viewModelScope)
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