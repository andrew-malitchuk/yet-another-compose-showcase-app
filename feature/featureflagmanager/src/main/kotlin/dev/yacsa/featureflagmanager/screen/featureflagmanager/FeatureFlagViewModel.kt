package dev.yacsa.featureflagmanager.screen.featureflagmanager

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.usecase.featureflag.LoadFeatureFlagUseCase
import dev.yacsa.domain.usecase.featureflag.NewUpdateLocalFeatureFlagUseCase
import dev.yacsa.featureflag.FeatureFlagModel
import dev.yacsa.model.mapper.FeatureFlagDomainRepoMapper
import dev.yacsa.platform.Theme
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
    private val updateLocalFeatureFlagUseCase: NewUpdateLocalFeatureFlagUseCase,
    private val loadFeatureFlagUseCase: LoadFeatureFlagUseCase,
    private val featureFlagDomainRepoMapper: FeatureFlagDomainRepoMapper,
    private val theme:Theme,
    ) : BaseViewModel<FeatureFlagUiState, FeatureFlagUiState.PartialState, FeatureFlagEvent, FeatureFlagIntent>(
    savedStateHandle,
    initialState
) , Theme by theme{
    init {
        logcat { "init" }
        acceptIntent(FeatureFlagIntent.GetFeatureFlags)
    }

    fun updateFeatureFlag(featureFlagModel: FeatureFlagModel) {
        viewModelScope.launch {
            updateLocalFeatureFlagUseCase(
                featureFlagModel.key,
                featureFlagModel.value,
            )
        }
    }

    private fun getAllFeatureFlags(): Flow<FeatureFlagUiState.PartialState> =
        flow {
            val list = loadFeatureFlagUseCase()
            list.fold(
                {
                    emit(FeatureFlagUiState.PartialState.Error(IllegalAccessError()))
                },
                { result ->
                    emit(FeatureFlagUiState.PartialState.Fetched(
                        result.map { ff ->
                            FeatureFlagModel(ff?.key ?: "NI", ff?.value)
                        } as ArrayList<FeatureFlagModel>
                    ))
                }
            )
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
                featureFlags = partialState.list,
            )

            is FeatureFlagUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
            )
        }
    }
}
