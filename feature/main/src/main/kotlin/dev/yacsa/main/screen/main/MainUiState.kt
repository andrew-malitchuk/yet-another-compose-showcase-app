package dev.yacsa.main.screen.main

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class MainUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        object Fetched : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }

}
