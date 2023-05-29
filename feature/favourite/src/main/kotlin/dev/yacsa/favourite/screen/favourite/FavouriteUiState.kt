package dev.yacsa.favourite.screen.favourite

import android.os.Parcelable
import dev.yacsa.model.model.BookUiModel
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class FavouriteUiState(
    val isContentLoading: Boolean = false,
    val isError: Boolean = false,
    val isResultLoading: Boolean = false,
    val favouriteList: List<BookUiModel>? = null,
) : Parcelable {

    sealed class PartialState {
        object ContentLoading : PartialState()
        data class ContentFetched(val favouriteList: List<BookUiModel>) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
