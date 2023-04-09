package dev.yacsa.books.screen.detalization

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.model.mapper.BookUiDomainMapper
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class DetalizationViewModel @Inject constructor(
    private val bookUiDomainMapper: BookUiDomainMapper,
    savedStateHandle: SavedStateHandle,
    initialState: DetalizationUiState,
) : BaseViewModel<DetalizationUiState, DetalizationUiState.PartialState, DetalizationEvent, DetalizationIntent>(
    savedStateHandle,
    initialState,
) {

    override fun mapIntents(intent: DetalizationIntent): Flow<DetalizationUiState.PartialState> {
        return when (intent) {
            is DetalizationIntent.GetBook -> getBook(intent.bookId)
            is DetalizationIntent.RefreshBook -> getBook(intent.bookId)
        }
    }

    private fun getBook(bookId: Int): Flow<DetalizationUiState.PartialState> = flow {
    }

    override fun reduceUiState(
        previousState: DetalizationUiState,
        partialState: DetalizationUiState.PartialState,
    ): DetalizationUiState {
        return when (partialState) {
            is DetalizationUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
            )
            is DetalizationUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                book = partialState.value,
                isError = false,
            )
            is DetalizationUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
            )
        }
    }
}
