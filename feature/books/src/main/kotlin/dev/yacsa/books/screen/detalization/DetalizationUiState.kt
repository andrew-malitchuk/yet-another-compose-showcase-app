package dev.yacsa.books.screen.detalization

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import dev.yacsa.model.model.BookUiModel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class DetalizationUiState(
    val isLoading: Boolean = false,
    val book: BookUiModel? = null,
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        data class Fetched(val value: BookUiModel) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
