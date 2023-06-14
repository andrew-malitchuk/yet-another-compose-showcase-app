package dev.yacsa.main.screen.main

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class MainUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val routeDestination: RouteDestination? =null
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        data class Fetched(val routeDestination: RouteDestination) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }

}
