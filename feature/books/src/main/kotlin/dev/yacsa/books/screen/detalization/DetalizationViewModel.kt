package dev.yacsa.books.screen.detalization

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.usecase.books.GetOrLoadBookUseCase
import dev.yacsa.model.mapper.BookUiDomainMapper
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class DetalizationViewModel @Inject constructor(
    private val bookUiDomainMapper: BookUiDomainMapper,
    private val getOrLoadBookUseCase: GetOrLoadBookUseCase,
    savedStateHandle: SavedStateHandle,
    initialState: DetalizationUiState,
) : BaseViewModel<DetalizationUiState, DetalizationUiState.PartialState, DetalizationEvent, DetalizationIntent>(
    savedStateHandle,
    initialState,
) {

    private val bookId: Int = checkNotNull(savedStateHandle["bookId"])

    init {
        logcat{"bookId:$bookId"}
        acceptIntent(DetalizationIntent.GetBook(bookId))
    }

    override fun mapIntents(intent: DetalizationIntent): Flow<DetalizationUiState.PartialState> {
        return when (intent) {
            is DetalizationIntent.GetBook -> getBook(intent.bookId)
            is DetalizationIntent.RefreshBook -> getBook(intent.bookId)
        }
    }

    private fun getBook(bookId: Int): Flow<DetalizationUiState.PartialState> = flow {
        emit(DetalizationUiState.PartialState.Loading)
        val result = getOrLoadBookUseCase(bookId)
        if (result == null) {
            emit(DetalizationUiState.PartialState.Error(NoSuchElementException()))
        } else {
            emit(
                DetalizationUiState.PartialState.Fetched(
                    bookUiDomainMapper.toUi(result)
                )
            )
        }

    }

    override fun reduceUiState(
        previousState: DetalizationUiState,
        partialState: DetalizationUiState.PartialState,
    ): DetalizationUiState {
        return when (partialState) {
            is DetalizationUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
                book = null
            )

            is DetalizationUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                book = partialState.value,
                isError = false,
            )

            is DetalizationUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
                book = null
            )
        }
    }
}
