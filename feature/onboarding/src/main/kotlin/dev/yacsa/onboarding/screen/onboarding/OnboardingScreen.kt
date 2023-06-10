package dev.yacsa.onboarding.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.*
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.onboarding.screen.onboarding.item.OnboardingItem
import dev.yacsa.ui.R
import dev.yacsa.ui.composable.theme.detectThemeMode
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingRoute(
    onboardingViewModel: OnboardingViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onDoneClick: () -> Unit,
) {

    val onboardingViewModel: OnboardingViewModel = hiltViewModel()

    val currentTheme  by onboardingViewModel.currentTheme
    val isDarkTheme = currentTheme?.detectThemeMode()?:false

    YacsaTheme(isDarkTheme) {
        OnboardingScreen(
            onBackClick,
            onDoneClick,
            onboardingViewModel
        )
    }

}

// TODO: fix & add route
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onBackClick: () -> Unit,
    onDoneClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel
) {

    val state = rememberPagerState()
    val scope = rememberCoroutineScope()

    val systemUiController = rememberSystemUiController()

    with(systemUiController) {
        setSystemBarsColor(
            color = YacsaTheme.colors.background
        )
        setNavigationBarColor(
            color = YacsaTheme.colors.background
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = YacsaTheme.colors.background),
    ) {
        TopSection(
            buttonType = onboardingViewModel.buttonType,
            onBackClick = onBackClick,
            onSkipClick = {
                scope.launch {
                    with(state) {
                        if (this.currentPage != this.pageCount - 1) {
                            state.animateScrollToPage(state.currentPage + 1)
                        } else {
                            with(onboardingViewModel) {
                                updateStartUpConfigure()
                                getStartUpConfigure()
                            }
                            onDoneClick()
                        }
                    }
                }
            },
        )
        HorizontalPager(
            count = onboardingViewModel.onboardingPages.size,
            state = state,
            modifier = Modifier
                .weight(1f, true),
        ) { page ->
            if (this.currentPage == (state.pageCount - 1)) {
                onboardingViewModel.buttonType = OnboardingViewModel.ButtonType.SKIP
            } else {
                onboardingViewModel.buttonType = OnboardingViewModel.ButtonType.NEXT
            }
            onboardingViewModel.onboardingPages[page].let {
                OnboardingItem(
                    it.imageId,
                    it.header,
                    it.caption,
                    state = state,
                )
            }
        }
        Spacer(modifier = Modifier.height(YacsaTheme.spacing.small))
        BottomSection(state)
    }
}

@Composable
fun TopSection(
    onBackClick: () -> Unit = {},
    onSkipClick: () -> Unit = {},
    buttonType: OnboardingViewModel.ButtonType,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(YacsaTheme.spacing.medium),
    ) {
        SmallFloatingActionButton(
            onClick = { onBackClick() },
            containerColor = YacsaTheme.colors.accent
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_caret_left_regular_24),
                contentDescription = null,
                tint = YacsaTheme.colors.primary
            )
        }
        TextButton(
            onClick = onSkipClick,
            modifier = Modifier.align(Alignment.CenterEnd),
            contentPadding = PaddingValues(0.dp),
        ) {
            val textForButton = when (buttonType) {
                OnboardingViewModel.ButtonType.SKIP -> "Skip"
                OnboardingViewModel.ButtonType.NEXT -> "Next"
            }

            Text(
                // TODO: move
                text = textForButton,
                style = YacsaTheme.typography.title,
                color = YacsaTheme.colors.primary,
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomSection(state: PagerState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {
        HorizontalPagerIndicator(
            pagerState = state,
            activeColor = YacsaTheme.colors.accent,
            inactiveColor = YacsaTheme.colors.primary,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(bottom = YacsaTheme.spacing.medium),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen_Light() {
    YacsaTheme(useDarkTheme = false) {
    }
}
