package dev.yacsa.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.*
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.launch
import logcat.logcat

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onBackClick: () -> Unit,
    onDoneClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel = viewModel(),
) {

    val state = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = YacsaTheme.colors.primaryBackground)
    ) {
        TopSection(
            buttonType = onboardingViewModel.buttonType,
            onBackClick = onBackClick,
            onSkipClick = {
                scope.launch {
                    with(state) {
                        if (this.currentPage != this.pageCount-1) {
                            state.animateScrollToPage(state.currentPage + 1)
                        } else {
                            onDoneClick()
                        }
                    }
                }
            }
        )
        HorizontalPager(
            count = onboardingViewModel.onboardingPages.size,
            state = state,
            modifier = Modifier
                .weight(1f, true)
        ) { page ->
            if (this.currentPage == (state.pageCount - 1)) {
                onboardingViewModel.buttonType = OnboardingViewModel.ButtonType.SKIP
            } else {
                onboardingViewModel.buttonType = OnboardingViewModel.ButtonType.NEXT
            }
            onboardingViewModel.onboardingPages[page].let {
                OnBoardingItem(
                    it.imageId,
                    it.header,
                    it.caption,
                    state = state
                )
            }
        }
        BottomSection(state)
    }
}

@Composable
fun TopSection(
    onBackClick: () -> Unit = {},
    onSkipClick: () -> Unit = {},
    buttonType: OnboardingViewModel.ButtonType
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = null,
                tint = YacsaTheme.colors.primaryText
            )
        }
        TextButton(
            onClick = onSkipClick,
            modifier = Modifier.align(Alignment.CenterEnd),
            contentPadding = PaddingValues(0.dp)
        ) {

            val textForButton = when (buttonType) {
                OnboardingViewModel.ButtonType.SKIP -> "Skip"
                OnboardingViewModel.ButtonType.NEXT -> "Next"
            }

            Text(
                // TODO: move
                text = textForButton,
                color = YacsaTheme.colors.primaryText
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingItem(
    @DrawableRes imageId: Int,
    header: String,
    caption: String,
    state: PagerState
) {
    val position = state.currentPageOffset
    val scaleMultiplier = 0.25
    val minScale = 0.75
    val scaleValue = if (position < 0) {
        (((minScale - (-position) * scaleMultiplier).toFloat() + scaleMultiplier).toFloat())
    } else if (position > 0 && position <= 1) {
        (((minScale - position * scaleMultiplier).toFloat() + scaleMultiplier).toFloat())
    } else {
        1f
    }
    val alphaValue = if (position < 0) {
        (1 - kotlin.math.abs(position))
    } else if (position > 0 && position <= 1) {
        (1 - position)
    } else {
        1f
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = Modifier
                .padding(
                    start = 50.dp,
                    end = 50.dp,
                )
                .scale(scaleValue)
                .alpha(alphaValue),
        )
        Spacer(
            modifier = Modifier.height(26.dp)
        )
        Text(
            text = header,
            style = YacsaTheme.typography.heading,
            modifier = Modifier.alpha(alphaValue),
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = caption,
            style = YacsaTheme.typography.caption,
            modifier = Modifier.alpha(alphaValue),
        )
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomSection(state: PagerState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        HorizontalPagerIndicator(
            pagerState = state,
            activeColor = YacsaTheme.colors.primaryText,
            inactiveColor = YacsaTheme.colors.secondaryText,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(bottom = 16.dp)
        )
    }

}

@OptIn(ExperimentalPagerApi::class)
fun foo(state: PagerState) {

}


//@Preview(showBackground = true)
//@Composable
//fun PreviewOnboardingScreen_Dark() {
//    YacsaTheme(useDarkTheme = true) {
//        OnboardingScreen(onboardingViewModel = OnboardingViewModel(), onClick = {})
//    }
//}
//
@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen_Light() {
    YacsaTheme(useDarkTheme = false) {
        OnboardingScreen({}, {}, OnboardingViewModel())
    }
}