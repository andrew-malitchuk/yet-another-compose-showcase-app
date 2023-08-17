package dev.yacsa.analytics.screen.analytics

import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.error.DataError
import dev.yacsa.domain.error.NoDataError
import dev.yacsa.domain.usecase.analytics.ClearAnalyticsUseCase
import dev.yacsa.domain.usecase.analytics.GetAnalyticsUseCase
import dev.yacsa.model.mapper.AnalyticsUiDomainMapper
import dev.yacsa.platform.Theme
import dev.yacsa.platform.string.UiText
import dev.yacsa.platform.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class AnalyticsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: AnalyticsUiState,
    private val getAnalyticsUseCase: GetAnalyticsUseCase,
    private val clearAnalyticsUseCase: ClearAnalyticsUseCase,
    private val analyticsUiDomainMapper: AnalyticsUiDomainMapper,
    private val theme: Theme,
) : BaseViewModel<AnalyticsUiState, AnalyticsUiState.PartialState, AnalyticsEvent, AnalyticsIntent>(
    savedStateHandle,
    initialState,
),
    Theme by theme {

    init {
        acceptIntent(AnalyticsIntent.GetList)
    }

    override fun mapIntents(intent: AnalyticsIntent): Flow<AnalyticsUiState.PartialState> {
        return when (intent) {
            is AnalyticsIntent.GetList -> getAnalytics()
            is AnalyticsIntent.Delete -> clear()
        }
    }

    private fun getAnalytics(): Flow<AnalyticsUiState.PartialState> =
        flow<AnalyticsUiState.PartialState> {
            // Dummy load
            delay(1_000L)

            getAnalyticsUseCase().fold(
                { error ->
                    when (error) {
                        is NoDataError -> {
                            emit(AnalyticsUiState.PartialState.Error(NoSuchElementException()))
                        }

                        else -> {
                            emit(AnalyticsUiState.PartialState.Error(Exception(UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).toString())))
                        }
                    }
                },
                { list ->
                    emit(AnalyticsUiState.PartialState.Fetched(list.map(analyticsUiDomainMapper::toUi)))
                },
            )
        }.onStart {
            emit(AnalyticsUiState.PartialState.Loading)
        }

    private fun clear(): Flow<AnalyticsUiState.PartialState> =
        flow<AnalyticsUiState.PartialState> {
            clearAnalyticsUseCase().fold(
                {
                    emit(AnalyticsUiState.PartialState.Fetched(emptyList()))
                },
                { error ->
                    when (error) {
                        is DataError -> {
                            emit(AnalyticsUiState.PartialState.Error(error.throwable))
                        }

                        else -> {
                            emit(AnalyticsUiState.PartialState.Error(Throwable(UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).toString())))
                        }
                    }
                },
            )
        }.onStart {
            emit(AnalyticsUiState.PartialState.Loading)
        }

    override fun reduceUiState(
        previousState: AnalyticsUiState,
        partialState: AnalyticsUiState.PartialState,
    ): AnalyticsUiState {
        return when (partialState) {
            is AnalyticsUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
            )

            is AnalyticsUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                isError = false,
                analytics = partialState.analytics,
            )

            AnalyticsUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
            )
        }
    }
}
