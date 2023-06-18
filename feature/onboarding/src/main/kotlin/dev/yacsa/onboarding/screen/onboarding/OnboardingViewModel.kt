package dev.yacsa.onboarding.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.analytics.event.ContentViewAnalyticModel
import dev.yacsa.analytics.event.CustomAnalyticModel
import dev.yacsa.analytics.event.UserPropertyAnalyticModel
import dev.yacsa.dispatcher.AnalyticDispatcher
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.domain.usecase.startupconfigure.NewGetStartUpConfigureUseCase
import dev.yacsa.domain.usecase.startupconfigure.NewUpdateStartUpConfigureUseCase
import dev.yacsa.platform.Theme
import dev.yacsa.platform.viewmodel.BaseViewModel
import dev.yacsa.ui.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    initialState: OnboardingUiState,
    private val updateStartUpConfigureUseCase: NewUpdateStartUpConfigureUseCase,
    private val getStartUpConfigureUseCase: NewGetStartUpConfigureUseCase,
    private val analyticDispatcher: AnalyticDispatcher,
    private val theme:Theme
) : BaseViewModel<OnboardingUiState, OnboardingUiState.PartialState, OnboardingEvent, OnboardingIntent>(
    savedStateHandle,
    initialState,
), Theme by theme{

    init {
        viewModelScope.launch {
            getTheme()
        }
        viewModelScope.launch {
            analyticDispatcher.sendEvent(
                object : ContentViewAnalyticModel() {
                    override val viewName = "onboarding"

                }
            )

            analyticDispatcher.sendEvent(
                object : CustomAnalyticModel() {
                    override var eventName: String = "foobar"

                    override fun getParameters(): Map<String, Any> = mapOf(
                        "bar" to true,
                        "foo" to 1
                    )

                }
            )
            analyticDispatcher.setUserProperty(object : UserPropertyAnalyticModel() {
                override val key: String = "name"
                override val value: Any = "Andrew"
            })
        }
    }

    fun updateStartUpConfigure() {
        viewModelScope.launch {
            updateStartUpConfigureUseCase(StartUpConfigureDomainModel(true))
        }
    }

    fun getStartUpConfigure() {
        viewModelScope.launch {
            logcat { getStartUpConfigureUseCase().toString() }
        }
    }

    var buttonType: ButtonType by mutableStateOf(ButtonType.NEXT)

    var onboardingPages = listOf(
        OnboadringPage(
            R.drawable.illustration_mobile_application,
            dev.yacsa.localization.R.string.onboarding_item_title_1,
            dev.yacsa.localization.R.string.onboarding_item_description_1,
        ),
        OnboadringPage(
            R.drawable.illustration_mobile_encryption,
            dev.yacsa.localization.R.string.onboarding_item_title_2,
            dev.yacsa.localization.R.string.onboarding_item_description_2,
        ),
        OnboadringPage(
            R.drawable.illustration_mobile_interface,
            dev.yacsa.localization.R.string.onboarding_item_title_3,
            dev.yacsa.localization.R.string.onboarding_item_description_3,
        ),
    )

    data class OnboadringPage(
        @DrawableRes val imageId: Int,
        @StringRes val header: Int,
        @StringRes val caption: Int,
    )

    enum class ButtonType {
        SKIP, NEXT
    }

    override fun mapIntents(intent: OnboardingIntent): Flow<OnboardingUiState.PartialState> {
        return when (intent) {
            is OnboardingIntent.GetStatus -> TODO()
        }
    }

    override fun reduceUiState(
        previousState: OnboardingUiState,
        partialState: OnboardingUiState.PartialState,
    ): OnboardingUiState {
        return when (partialState) {
            is OnboardingUiState.PartialState.Error -> previousState.copy(
                isLoading = false,
                isError = true,
            )

            OnboardingUiState.PartialState.Fetched -> previousState.copy(
                isLoading = false,
                isError = false,
            )

            OnboardingUiState.PartialState.Loading -> previousState.copy(
                isLoading = true,
                isError = false,
            )
        }
    }
}
