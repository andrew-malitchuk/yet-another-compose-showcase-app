package dev.yacsa.settings.screen.settings

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class SettingsUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val lang: String?=null,
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        data class Fetched(val lang: String?) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
