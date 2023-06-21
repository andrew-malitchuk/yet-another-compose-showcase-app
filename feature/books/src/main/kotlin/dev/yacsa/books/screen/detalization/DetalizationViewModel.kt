package dev.yacsa.books.screen.detalization

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.error.NoDataError
import dev.yacsa.domain.usecase.books.MarkFavouriteBook
import dev.yacsa.domain.usecase.books.NewGetOrLoadBookUseCase
import dev.yacsa.model.mapper.NewBooksUiDomainMapper
import dev.yacsa.platform.Theme
import dev.yacsa.platform.connection.ConnectivityObserver
import dev.yacsa.platform.string.UiText
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class DetalizationViewModel @Inject constructor(
    private val bookUiDomainMapper: NewBooksUiDomainMapper,
    private val getOrLoadBookUseCase: NewGetOrLoadBookUseCase,
    private val markFavouriteBook: MarkFavouriteBook,
    private val theme: Theme,
    var connectivityObserver: ConnectivityObserver,
    savedStateHandle: SavedStateHandle,
    initialState: DetalizationUiState,
) : BaseViewModel<DetalizationUiState, DetalizationUiState.PartialState, DetalizationEvent, DetalizationIntent>(
    savedStateHandle,
    initialState,
), Theme by theme {

    var checked: MutableState<Boolean?> =
        mutableStateOf(null)

    var favouriteState: MutableState<Boolean?> =
        mutableStateOf(false)

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
            is DetalizationIntent.OnShareClick -> onShareClick(intent.id)
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
                        emit(DetalizationUiState.PartialState.Error(Exception(UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).toString())))
                    }
                }
            },
            {
                checked.value= it.favourite == true
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

    private fun onShareClick(id:Int): Flow<DetalizationUiState.PartialState> {
        publishEvent(DetalizationEvent.ShareDeeplink(generateDeeplink(id)))
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

    // TODO: recode
    fun changeFavourite(isFavourite: Boolean) {
        viewModelScope.launch {
            if (uiState.value.book?.isFavourite!=isFavourite) {
                markFavouriteBook(bookId, isFavourite)
                favouriteState.value=true
            }
        }
    }


    private fun generateDeeplink(id:Int):String{
        return "yacsa://book/$id"
    }
}
