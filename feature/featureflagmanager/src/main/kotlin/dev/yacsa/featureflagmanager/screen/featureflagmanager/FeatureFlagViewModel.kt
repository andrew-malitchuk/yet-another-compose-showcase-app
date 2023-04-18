package dev.yacsa.featureflagmanager.screen.featureflagmanager

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.featureflag.FeatureFlagModel
import dev.yacsa.platform.viewmodel.BaseViewModel
import dev.yacsa.repository.FeatureFlagRepository
import dev.yacsa.repository.model.FeatureFlagRepoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class FeatureFlagViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: FeatureFlagUiState,
    private val featureFlagRepository: FeatureFlagRepository
) : BaseViewModel<FeatureFlagUiState, FeatureFlagUiState.PartialState, FeatureFlagEvent, FeatureFlagIntent>(
    savedStateHandle,
    initialState,
) {

    init {
        logcat { "init" }
        acceptIntent(FeatureFlagIntent.GetFeatureFlags)
    }


    fun updateFeatureFlag(featureFlagModel: FeatureFlagModel) {
        viewModelScope.launch {
            featureFlagRepository.updateLocalFeatureFlag(
                FeatureFlagRepoModel(
                    featureFlagModel.key,
                    featureFlagModel.value
                )
            )
        }
    }

    private fun getAllFeatureFlags(): Flow<FeatureFlagUiState.PartialState> =
        flow {
            val list = featureFlagRepository.loadFeatureFlags()
            if (list.isSuccess) {
                emit(
                    FeatureFlagUiState.PartialState.Fetched(
                        (list.getOrNull()?.map {
                            FeatureFlagModel(it.key, it.value)
                        } ?: arrayListOf()) as ArrayList<FeatureFlagModel>
                    )
                )
            } else {
                emit(FeatureFlagUiState.PartialState.Error(list.exceptionOrNull()!!))
            }
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