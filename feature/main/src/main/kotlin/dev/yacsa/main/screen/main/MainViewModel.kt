package dev.yacsa.main.screen.main

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.usecase.startupconfigure.NewGetStartUpConfigureUseCase
import dev.yacsa.platform.Theme
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val theme: Theme,
    private val getStartUpConfigureUseCase: NewGetStartUpConfigureUseCase,
    savedStateHandle: SavedStateHandle,
    initialState: MainUiState,
) : BaseViewModel<MainUiState, MainUiState.PartialState, MainEvent, MainIntent>(
    savedStateHandle,
    initialState,
), Theme by theme {

    init {
        acceptIntent(MainIntent.GetStatus)
    }

    override fun mapIntents(intent: MainIntent): Flow<MainUiState.PartialState> {
        return when (intent) {
            MainIntent.GetStatus -> getStatus()
        }
    }

    private fun getStatus(): Flow<MainUiState.PartialState> {
        return flow {
            getStartUpConfigureUseCase().fold(
                {
                    emit(MainUiState.PartialState.Error(IllegalStateException()))
                }, { status ->
                    when {
                        status.hasBeenOnboardingShown -> {
                            emit(MainUiState.PartialState.Fetched(RouteDestination.MAIN))
                        }
                        else -> {
                            emit(MainUiState.PartialState.Fetched(RouteDestination.ONBOARDING))
                        }
                    }

                }
            )
        }
    }

    override fun reduceUiState(
        previousState: MainUiState,
        partialState: MainUiState.PartialState
    ): MainUiState {
        return when (partialState) {
            is MainUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
                routeDestination = null
            )

            is MainUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
                routeDestination = null
            )

            is MainUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                isError = false,
                routeDestination = partialState.routeDestination
            )
        }
    }

}