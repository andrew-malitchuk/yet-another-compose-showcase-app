package dev.yacsa.books.screen.detalization

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.error.NoDataError
import dev.yacsa.domain.usecase.books.NewGetOrLoadBookUseCase
import dev.yacsa.model.mapper.NewBooksUiDomainMapper
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class DetalizationViewModel @Inject constructor(
    private val bookUiDomainMapper: NewBooksUiDomainMapper,
    private val getOrLoadBookUseCase: NewGetOrLoadBookUseCase,
    savedStateHandle: SavedStateHandle,
    initialState: DetalizationUiState,
) : BaseViewModel<DetalizationUiState, DetalizationUiState.PartialState, DetalizationEvent, DetalizationIntent>(
    savedStateHandle,
    initialState,
) {

    private val bookId: Int = checkNotNull(savedStateHandle["bookId"])

    init {
        logcat { "bookId:$bookId" }
        acceptIntent(DetalizationIntent.GetBook(bookId))
    }

    override fun mapIntents(intent: DetalizationIntent): Flow<DetalizationUiState.PartialState> {
        return when (intent) {
            is DetalizationIntent.GetBook -> getBook(intent.bookId)
            is DetalizationIntent.RefreshBook -> getBook(intent.bookId)
            is DetalizationIntent.OnLinkClick -> onLickClick(intent.link)
        }
    }

    private fun getBook(bookId: Int): Flow<DetalizationUiState.PartialState> = flow {
        getOrLoadBookUseCase(bookId).fold(
            { error ->
                when (error) {
                    is NoDataError -> {
                        emit(DetalizationUiState.PartialState.Error(NoSuchElementException()))
                    }

                    else -> {
                        emit(DetalizationUiState.PartialState.Error(Exception("SWW")))
                    }
                }
            },
            {
                emit(
                    DetalizationUiState.PartialState.Fetched(
                        bookUiDomainMapper.toUi(it),
                    ),
                )
            },
        )
    }.onStart {
        emit(DetalizationUiState.PartialState.Loading)
    }


    private fun onLickClick(uri: String): Flow<DetalizationUiState.PartialState> {
        publishEvent(DetalizationEvent.OpenWebBrowserWithDetails(uri))
        return emptyFlow()
    }

    override fun reduceUiState(
        previousState: DetalizationUiState,
        partialState: DetalizationUiState.PartialState,
    ): DetalizationUiState {
        return when (partialState) {
            is DetalizationUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
                book = null,
            )

            is DetalizationUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                book = partialState.value,
                isError = false,
            )

            is DetalizationUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
                book = null,
            )
        }
    }
}
