package dev.yacsa.info.screen.info.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.yacsa.info.screen.info.InfoUiState
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.composable.content.ContentIsLoading
import dev.yacsa.ui.theme.YacsaTheme
import kotlin.math.abs

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class,
    ExperimentalMaterial3Api::class,
)
@Composable
fun ContentFetched(
    modifier: Modifier = Modifier.fillMaxSize(),
    innerPadding: PaddingValues,
    state: LazyListState,
    topAppBarState: TopAppBarState,
    uiState: InfoUiState,
) {
    val corner =
        YacsaTheme.corners.medium - (YacsaTheme.corners.medium * abs(topAppBarState.collapsedFraction))
    val systemUiController = rememberSystemUiController()

    when {
        uiState.isLoading -> {
            systemUiController.setNavigationBarColor(
                color = YacsaTheme.colors.navigationBar,
            )
            ContentIsLoading()
        }

        uiState.isError -> {
            systemUiController.setNavigationBarColor(
                color = YacsaTheme.colors.statusBar,
            )
            dev.yacsa.ui.composable.content.ContentError(
                errorMessage = UiText.StringResource(dev.yacsa.localization.R.string.errors_sww).asString(),
            ) {
            }
        }

        else -> {
            systemUiController.apply {
                setSystemBarsColor(
                    color = YacsaTheme.colors.statusBar,
                )
                setNavigationBarColor(
                    color = YacsaTheme.colors.surface,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(YacsaTheme.colors.background)
                    .padding(innerPadding),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(topStart = corner, topEnd = corner))
                        .background(YacsaTheme.colors.surface),
                ) {
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun Preview_ContentFetched_Light() {
    YacsaTheme(false) {
        ContentFetched(
            uiState = InfoUiState(),
            innerPadding = PaddingValues(YacsaTheme.spacing.small),
            topAppBarState = rememberTopAppBarState(),
            state = rememberLazyListState(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun Preview_ContentFetched_Dark() {
    YacsaTheme(true) {
        ContentFetched(
            uiState = InfoUiState(),
            innerPadding = PaddingValues(YacsaTheme.spacing.small),
            topAppBarState = rememberTopAppBarState(),
            state = rememberLazyListState(),
        )
    }
}
