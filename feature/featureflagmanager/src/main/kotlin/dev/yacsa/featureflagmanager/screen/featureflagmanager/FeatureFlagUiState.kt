package dev.yacsa.featureflagmanager.screen.featureflagmanager

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import dev.yacsa.featureflag.BaseFeatureFlagModel
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class FeatureFlagUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val featureFlags: List<BaseFeatureFlagModel>? = null
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        data class Fetched(val list: List<BaseFeatureFlagModel>) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
