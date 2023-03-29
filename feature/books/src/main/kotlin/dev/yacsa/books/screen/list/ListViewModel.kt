package dev.yacsa.books.screen.list

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.domain.usecase.GetBooksUseCase
import dev.yacsa.domain.usecase.GetStartUpConfigureUseCase
import dev.yacsa.domain.usecase.LoadBooksUseCase
import dev.yacsa.domain.usecase.RefreshBooksUseCase
import dev.yacsa.model.mapper.BookUiDomainMapper
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.*
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

    init {
        acceptIntent(ListIntent.GetBooks)
    }

    override fun mapIntents(intent: ListIntent): Flow<ListUiState.PartialState> {
        return when (intent) {
            is ListIntent.GetBooks -> getBooks()
            is ListIntent.RefreshBooks -> refreshBooks()
            is ListIntent.BookClicked -> bookClicked(intent.foo)
        }
    }

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

    private fun refreshBooks(): Flow<ListUiState.PartialState> = flow {
        refreshBooksUseCase()
            .onFailure {
                emit(ListUiState.PartialState.Error(it))
            }
    }

    private fun bookClicked(foo: String): Flow<ListUiState.PartialState> {
        publishEvent(ListEvent.OnBookClick(foo))
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