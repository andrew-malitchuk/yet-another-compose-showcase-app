package dev.yacsa.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.*
import dev.yacsa.ui.theme.YacsaTheme
import logcat.logcat
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.lang.Math.abs

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel = viewModel(),
) {

    val state = rememberPagerState()

    Column(
        modifier = Modifier.fillMaxSize().background(color = YacsaTheme.colors.primaryBackground)
    ) {
        TopSection(
            onBackClick = {

            },
            onSkipClick = {

            }
        )
        HorizontalPager(
            count = onboardingViewModel.onboardingPages.size,
            state = state,
            modifier = Modifier
                .weight(1f, true)
        ) { page ->

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
    onSkipClick: () -> Unit = {}
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
            Text(
                // TODO: move
                text = "Skip",
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
            modifier = Modifier.align(Alignment.CenterVertically).padding(bottom = 16.dp)
        )
    }

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
        OnboardingScreen(onboardingViewModel = OnboardingViewModel(), onClick = {})
    }
}