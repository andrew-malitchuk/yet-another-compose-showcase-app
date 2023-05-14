package dev.yacsa.onboarding.screen.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.*
import dev.yacsa.onboarding.screen.onboarding.item.OnboardingItem
import dev.yacsa.ui.R
import dev.yacsa.ui.theme.YacsaTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onBackClick: () -> Unit,
    onDoneClick: () -> Unit,
    onboardingViewModel: OnboardingViewModel = hiltViewModel(),
) {
    val state = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = YacsaTheme.colors.primaryBackground),
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
        Spacer(modifier = Modifier.height(6.dp))
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
            .padding(12.dp),
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart),
        ) {
            Icon(
                imageVector = Icons.Outlined.KeyboardArrowLeft,
                contentDescription = null,
                tint = YacsaTheme.colors.primaryText,
            )
        }
        SmallFloatingActionButton(onClick = { onBackClick() }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_caret_left_regular_24),
                contentDescription = null
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
                color = YacsaTheme.colors.primaryText,
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
            activeColor = YacsaTheme.colors.primaryText,
            inactiveColor = YacsaTheme.colors.secondaryText,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(bottom = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingScreen_Light() {
    YacsaTheme(useDarkTheme = false) {

    }
}
