package dev.yacsa.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(

) : ViewModel() {


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