package dev.yacsa.onboarding.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.domain.usecase.startupconfigure.NewGetStartUpConfigureUseCase
import dev.yacsa.domain.usecase.startupconfigure.NewUpdateStartUpConfigureUseCase
import dev.yacsa.ui.R
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val updateStartUpConfigureUseCase: NewUpdateStartUpConfigureUseCase,
    private val getStartUpConfigureUseCase: NewGetStartUpConfigureUseCase,
) : ViewModel() {

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
            R.drawable.ic_mobile_application,
            "header #1",
            "caption #1",
        ),
        OnboadringPage(
            R.drawable.ic_mobile_application,
            "header #2",
            "caption #2",
        ),
        OnboadringPage(
            R.drawable.ic_mobile_application,
            "header #3",
            "caption #3",
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
}
