package dev.yacsa.analytics.screen.analytics

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import dev.yacsa.model.model.analytics.AnalyticsUiModel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class AnalyticsUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val analytics: List<AnalyticsUiModel> = emptyList()
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        data class Fetched(val analytics: List<AnalyticsUiModel>) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
