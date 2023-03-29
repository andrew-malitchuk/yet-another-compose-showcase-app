package dev.yacsa.books.screen.list

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import dev.yacsa.model.model.BookUiModel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class ListUiState(
    val isLoading: Boolean = false,
    val books: List<BookUiModel> = emptyList(),
    val isError: Boolean = false
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        data class Fetched(val list: List<BookUiModel>) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}