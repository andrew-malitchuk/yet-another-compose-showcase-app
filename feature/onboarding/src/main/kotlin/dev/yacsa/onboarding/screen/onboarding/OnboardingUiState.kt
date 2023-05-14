package dev.yacsa.onboarding.screen.onboarding

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class OnboardingUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        object Fetched : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
