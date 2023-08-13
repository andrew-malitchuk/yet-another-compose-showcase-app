package dev.yacsa.featureflagmanager.screen.featureflagmanager.content

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
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
import dev.yacsa.featureflag.FeatureFlagModel
import dev.yacsa.featureflagmanager.screen.featureflagmanager.FeatureFlagUiState
import dev.yacsa.featureflagmanager.screen.featureflagmanager.item.ItemFetched
import dev.yacsa.platform.string.UiText
import dev.yacsa.ui.composable.content.ContentIsLoading
import dev.yacsa.ui.composable.content.ContentNoData
import dev.yacsa.ui.theme.YacsaTheme
import io.github.serpro69.kfaker.Faker
import logcat.logcat

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
    uiState: FeatureFlagUiState,
    isEnabled: (FeatureFlagModel) -> Unit,
    isActive: (FeatureFlagModel) -> Unit,
) {
    val corner =
        YacsaTheme.corners.medium - (YacsaTheme.corners.medium * Math.abs(topAppBarState.collapsedFraction))
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
                    if (uiState.featureFlags.isNullOrEmpty()) {
                        ContentNoData(
                            message = UiText.StringResource(dev.yacsa.localization.R.string.errors_no_data).asString(),
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            state = state,
                            contentPadding = PaddingValues(YacsaTheme.spacing.small),
                            verticalArrangement = Arrangement.spacedBy(YacsaTheme.spacing.small),
                        ) {
                            items(items = uiState.featureFlags ?: emptyList()) { item ->
                                ItemFetched(item = item, isEnabled = {
                                    logcat { it.toString() }
                                    isEnabled(
                                        item.also {
                                            it.value = null
                                        },
                                    )
                                }, isActive = { value ->
                                    logcat { value.toString() }
                                    isActive(
                                        item.also {
                                            it.value = value
                                        },
                                    )
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun Preview_ContentFetched_Light() {
    val faker = Faker()
    YacsaTheme(false) {
        ContentFetched(
            uiState = FeatureFlagUiState(
                featureFlags = listOf(
                    FeatureFlagModel(faker.quote.fortuneCookie()),
                ),
            ),
            isEnabled = {},
            isActive = {},
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
    val faker = Faker()
    YacsaTheme(true) {
        ContentFetched(
            uiState = FeatureFlagUiState(
                featureFlags = listOf(
                    FeatureFlagModel(faker.quote.fortuneCookie()),
                ),
            ),
            isEnabled = {},
            isActive = {},
            innerPadding = PaddingValues(YacsaTheme.spacing.small),
            topAppBarState = rememberTopAppBarState(),
            state = rememberLazyListState(),
        )
    }
}
