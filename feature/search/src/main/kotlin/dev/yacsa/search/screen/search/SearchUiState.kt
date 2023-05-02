package dev.yacsa.search.screen.search

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import dev.yacsa.model.model.BookUiModel
import dev.yacsa.model.model.SearchHistoryUiModel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class SearchUiState(
    val isContentLoading: Boolean = false,
    val isError: Boolean = false,
    val isResultLoading:Boolean=false,
    val topSearch: List<SearchHistoryUiModel>? = null,
    val resultSearch: List<BookUiModel>? = null,
) : Parcelable {

    sealed class PartialState {
        object ContentLoading : PartialState()
        object ResultLoading : PartialState()
        data class ContentFetched(val topSearch:  List<SearchHistoryUiModel>) : PartialState()
        data class ResultFetched(val resultSearch: List<BookUiModel>) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
