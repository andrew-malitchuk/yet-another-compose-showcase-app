package dev.yacsa.books.screen.list

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import androidx.paging.PagingData
import dev.yacsa.model.model.BookUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class ListUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        object Fetched : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}