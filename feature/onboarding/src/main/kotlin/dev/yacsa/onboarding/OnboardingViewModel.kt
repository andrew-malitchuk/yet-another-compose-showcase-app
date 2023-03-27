package dev.yacsa.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yacsa.domain.model.StartUpConfigureDomainModel
import dev.yacsa.domain.usecase.GetStartUpConfigureUseCase
import dev.yacsa.domain.usecase.UpdateStartUpConfigureUseCase
import kotlinx.coroutines.launch
import logcat.logcat
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val updateStartUpConfigureUseCase: UpdateStartUpConfigureUseCase,
    private val getStartUpConfigureUseCase: GetStartUpConfigureUseCase
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
            dev.yacsa.ui.R.drawable.ic_mobile_application,
            "header #1",
            "caption #1"
        ),
        OnboadringPage(
            dev.yacsa.ui.R.drawable.ic_mobile_application,
            "header #2",
            "caption #2"
        ),
        OnboadringPage(
            dev.yacsa.ui.R.drawable.ic_mobile_application,
            "header #3",
            "caption #3"
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