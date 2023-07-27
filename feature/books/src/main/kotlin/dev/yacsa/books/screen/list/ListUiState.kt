package dev.yacsa.books.screen.list

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import dev.yacsa.model.model.update.CheckUpdateUiModel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class ListUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isFeatureBlocked: Boolean = false,
    val isUpdateEnabled:Boolean=false
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        object Fetched : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
        object Blocked : PartialState()
        data class Update(val checkUpdateUiModel: CheckUpdateUiModel):PartialState()
    }
}
