package dev.yacsa.featureflagmanager.screen.featureflagmanager

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import dev.yacsa.featureflag.FooFlag
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class FeatureFlagUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val featureFlags: List<FooFlag>? = null
) : Parcelable {

    sealed class PartialState {
        object Loading : PartialState()
        data class Fetched(val list: ArrayList<FooFlag>) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
