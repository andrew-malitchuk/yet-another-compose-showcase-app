package dev.yacsa.search.screen.search

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import dev.yacsa.model.model.BookUiModel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class SearchUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val topSearch: List<BookUiModel>? = null,
    val resultSearch: List<BookUiModel>? = null,
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        data class Fetched(val topSearch:  ArrayList<BookUiModel>) : PartialState()
        data class Result(val resultSearch: ArrayList<BookUiModel>) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
