package dev.yacsa.favourite.screen.favourite

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.usecase.books.SubscribeToFavourite
import dev.yacsa.model.mapper.NewBooksUiDomainMapper
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    var subscribeToFavourite: SubscribeToFavourite,
    var bookUiDomainMapper: NewBooksUiDomainMapper,
    savedStateHandle: SavedStateHandle,
    initialState: FavouriteUiState,
) : BaseViewModel<FavouriteUiState, FavouriteUiState.PartialState, FavouriteEvent, FavouriteIntent>(
    savedStateHandle,
    initialState,
) {

    var flow: Flow<List<BookUiModel?>?>? = null

    init {
        acceptIntent(FavouriteIntent.GetFavourite)
    }

    override fun mapIntents(intent: FavouriteIntent): Flow<FavouriteUiState.PartialState> {
        return when (intent) {
            FavouriteIntent.GetFavourite -> getFavourite()
        }
    }

    override fun reduceUiState(
        previousState: FavouriteUiState,
        partialState: FavouriteUiState.PartialState
    ): FavouriteUiState {
        return when (partialState) {
            is FavouriteUiState.PartialState.ContentLoading -> previousState.copy(
                isContentLoading = true,
                isError = false,
            )

            is FavouriteUiState.PartialState.ContentFetched -> previousState.copy(
                isContentLoading = false,
                isError = false,
            )

            is FavouriteUiState.PartialState.Error -> previousState.copy(
                isContentLoading = false,
                isError = true,
            )
        }
    }

    private fun getFavourite(): Flow<FavouriteUiState.PartialState> =
        flow {
            subscribeToFavourite().fold(
                {
                    // TODO: fix
                    emit(FavouriteUiState.PartialState.Error(Exception()))
                },
                { flow ->
                    this@FavouriteViewModel.flow = flow.map { list ->
                        list?.filterNotNull()?.map {
                            bookUiDomainMapper.toUi(it)
                        }
                    }
                    emit(FavouriteUiState.PartialState.ContentFetched)
                }
            )
        }.onStart {
            FavouriteUiState.PartialState.ContentLoading
        }

}