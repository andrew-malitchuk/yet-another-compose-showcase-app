package dev.yacsa.onboarding.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.domain.usecase.startupconfigure.NewGetStartUpConfigureUseCase
import dev.yacsa.domain.usecase.startupconfigure.NewUpdateStartUpConfigureUseCase
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
) : BaseViewModel<OnboardingUiState, OnboardingUiState.PartialState, OnboardingEvent, OnboardingIntent>(
    savedStateHandle,
    initialState,
) {

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
            R.drawable.img_mobile_application,
            "Search",
            "Search and filter books",
        ),
        OnboadringPage(
            R.drawable.img_mobile_encryption,
            "Favourites",
            "Add books to bookshelf",
        ),
        OnboadringPage(
            R.drawable.img_mobile_interface,
            "Download",
            "All books are free to download",
        ),
    )

    data class OnboadringPage(
        @DrawableRes val imageId: Int,
        val header: String,
        val caption: String,
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
