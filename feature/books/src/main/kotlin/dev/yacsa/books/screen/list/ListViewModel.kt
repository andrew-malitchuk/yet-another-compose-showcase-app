package dev.yacsa.books.screen.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.books.featureflag.BooksFeatureFlag
import dev.yacsa.books.screen.list.pagination.BooksSource
import dev.yacsa.domain.usecase.RemoveAllBooksUseCase
import dev.yacsa.domain.usecase.books.GetBooksUseCase
import dev.yacsa.domain.usecase.books.LoadBooksUseCase
import dev.yacsa.domain.usecase.books.SaveBooksUseCase
import dev.yacsa.model.mapper.BookUiDomainMapper
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val loadBooksUseCase: LoadBooksUseCase,
    private val bookUiDomainMapper: BookUiDomainMapper,
    private val removeAllBooksUseCase: RemoveAllBooksUseCase,
    private val saveBooksUseCase: SaveBooksUseCase,
    private val booksFeatureFlag: BooksFeatureFlag,
    savedStateHandle: SavedStateHandle,
    initialState: ListUiState,
) : BaseViewModel<ListUiState, ListUiState.PartialState, ListEvent, ListIntent>(
    savedStateHandle,
    initialState,
) {

    init {
        logcat { "init" }
        acceptIntent(ListIntent.CheckFeatureBlock)
//        acceptIntent(ListIntent.GetBooks)
//        viewModelScope.launch {
//            logcat { booksFeatureFlag.isFoo().toString() }
//            logcat("isFeatureEnabled") { booksFeatureFlag.isFeatureEnabled().toString() }
//        }

    }

    override fun mapIntents(intent: ListIntent): Flow<ListUiState.PartialState> {
        return when (intent) {
            is ListIntent.GetBooks -> getBooks()
            is ListIntent.BookClicked -> bookClicked(intent.bookId)
            is ListIntent.CheckFeatureBlock -> checkFeatureStatus()
        }
    }

    var pagingDataFlow: Flow<PagingData<BookUiModel>>? = null

    private fun getBooks(): Flow<ListUiState.PartialState> = flow<ListUiState.PartialState> {
        logcat { "getBooks" }
        // Dummy load
        delay(5_000L)
        pagingDataFlow = Pager(PagingConfig(pageSize = 32)) {
            BooksSource(
                getBooksUseCase,
                loadBooksUseCase,
                bookUiDomainMapper,
                saveBooksUseCase,
            )
        }.flow.cachedIn(viewModelScope)
        emit(ListUiState.PartialState.Fetched)
    }.onStart {
        emit(ListUiState.PartialState.Loading)
    }

    private fun bookClicked(bookId: Int): Flow<ListUiState.PartialState> {
        publishEvent(ListEvent.OnBookClick(bookId))
        return emptyFlow()
    }

    private fun checkFeatureStatus(): Flow<ListUiState.PartialState> = flow {
        val isBooksFeatureEnabled = booksFeatureFlag.isFeatureEnabled()
        logcat("isBooksFeatureEnabled") { isBooksFeatureEnabled.toString() }
        if (isBooksFeatureEnabled) {
            acceptIntent(ListIntent.GetBooks)
        } else {
            emit(ListUiState.PartialState.Blocked)
        }
    }

    override fun reduceUiState(
        previousState: ListUiState,
        partialState: ListUiState.PartialState,
    ): ListUiState {
        return when (partialState) {
            is ListUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
                isFeatureBlocked = false
            )

            is ListUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                isError = false,
                isFeatureBlocked = false
            )

            is ListUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
                isFeatureBlocked = false
            )

            is ListUiState.PartialState.Blocked -> previousState.copy(
                isLoading = false,
                isError = false,
                isFeatureBlocked = true
            )
        }
    }
}
