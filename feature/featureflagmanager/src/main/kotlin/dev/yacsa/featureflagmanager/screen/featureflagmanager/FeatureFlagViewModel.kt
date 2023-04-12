package dev.yacsa.featureflagmanager.screen.featureflagmanager

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.featureflag.impl.container.FeatureFlagList
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class FeatureFlagViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: FeatureFlagUiState,
) : BaseViewModel<FeatureFlagUiState, FeatureFlagUiState.PartialState, FeatureFlagEvent, FeatureFlagIntent>(
    savedStateHandle,
    initialState,
) {


    init {
        logcat { "init" }
        acceptIntent(FeatureFlagIntent.GetFeatureFlags)
        viewModelScope.launch {

        }
    }

    private fun getAllFeatureFlags(): Flow<FeatureFlagUiState.PartialState> =
        flow {
            val list = FeatureFlagList().foo
            emit(FeatureFlagUiState.PartialState.Fetched(list))
        }


    override fun mapIntents(intent: FeatureFlagIntent): Flow<FeatureFlagUiState.PartialState> {
        return when (intent) {
            is FeatureFlagIntent.GetFeatureFlags -> getAllFeatureFlags()
            is FeatureFlagIntent.OnFeatureFlagClick -> TODO()
        }
    }

    override fun reduceUiState(
        previousState: FeatureFlagUiState,
        partialState: FeatureFlagUiState.PartialState,
    ): FeatureFlagUiState {
        return when (partialState) {
            is FeatureFlagUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
            )
            is FeatureFlagUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                isError = false,
                featureFlags = partialState.list
            )
            is FeatureFlagUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
            )
        }
    }

}