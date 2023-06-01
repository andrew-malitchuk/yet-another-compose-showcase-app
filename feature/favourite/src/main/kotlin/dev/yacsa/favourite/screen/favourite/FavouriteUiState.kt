package dev.yacsa.favourite.screen.favourite

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class FavouriteUiState(
    val isContentLoading: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        object ContentLoading : PartialState()
        object ContentFetched : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
