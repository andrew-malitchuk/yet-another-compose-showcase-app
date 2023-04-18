package dev.yacsa.featureflagmanager.screen.featureflagmanager

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import dev.yacsa.featureflag.FeatureFlagModel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class FeatureFlagUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val featureFlags: List<FeatureFlagModel>? = null,
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        data class Fetched(val list: ArrayList<FeatureFlagModel>) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
